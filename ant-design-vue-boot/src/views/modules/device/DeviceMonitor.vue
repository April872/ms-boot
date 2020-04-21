<template xmlns:background-color="http://www.w3.org/1999/xhtml">
  <a-row :gutter="8">
    <!-- 左侧设备树形结构-->
    <a-col :md="6" :sm="24">
      <a-card :bordered="false" style="overflow-x: scroll">
       
        <div style="background: #fff;padding-left:0px;height: 100%; margin-top: 0px">
          <a-alert type="info" :showIcon="true">
            <div slot="message">
              当前设备：
              <span v-if="this.currSelected.title">{{ getCurrSelectedTitle() }}</span>
              <a v-if="this.currSelected.title" style="margin-left: 10px" @click="onClearSelected">取消选择</a>
            </div>
          </a-alert>
          <!-- 树-->
         <a-col :md="10" :sm="24">
            <template>
              <a-dropdown :trigger="[this.dropTrigger]" @visibleChange="dropStatus">
                <span style="user-select: none">
                  <a-tree
                    showIcon
                    multiple
                    disableCheckbox
                    @select="onSelect"
                    @expand="onExpand"
                    :selectedKeys="selectedKeys"
                    :treeData="departTree"
                    :expandedKeys="iExpandedKeys"
                    :autoExpandParent="autoExpandParent"
                  >
                    <!-- 添加机构标识的图标 -->
                    <a-icon slot="org" type="appstore" theme="twoTone" />
                    <a-icon slot="dep" type="profile" />
                    <!-- 设备运行状态   0：停机 1：正常 2：告警  3：故障-->
                    <a-icon slot="stop" type="chrome" theme="filled"/>
                    <a-icon slot="normal" type="compass" spin theme="twoTone" twoToneColor="#00aa00" />
                    <a-icon slot="alarm" type="compass" spin theme="twoTone" twoToneColor="#ffaa00" />
                    <a-icon slot="error" type="compass" spin theme="twoTone" twoToneColor="#ff0000" />
                  </a-tree>
                </span>
                
              </a-dropdown>
            </template>
         </a-col>
        </div>
      </a-card>
    </a-col> 
    <!-- 表格-->
    <a-col :md="18" :sm="24">
      <a-card style="width: 100%">
            <!-- <a href="#" slot="extra">more</a> -->
        <a-row :gutter="24" >
          <a-col :xl="12">
            <d1-data-type1 :dataSource="tableDataSource1">  </d1-data-type1>
          </a-col>
          <a-col :xl="12">
            <d1-data-type1>  </d1-data-type1>
          </a-col>  
            
        </a-row>
        <!-- 对应的图形-->
        <a-row :gutter="24" >
          <a-col :xl="12">
           
            <line-chart-multid :fields="chartFields1" :dataSource="chartDataSource1" :title="chartTitle1" :height="300"></line-chart-multid>
           
          </a-col>
          <a-col :xl="12">
            <line-chart-multid :fields="chartFields1" :dataSource="chartDataSource1" :title="缸内温度实时监测图形" :height="300"></line-chart-multid>
          </a-col>  
           
        </a-row>
        <a-row :gutter="24">
          <a-col :xl="12">
            <d1-data-type1>  </d1-data-type1>
          </a-col>
          <a-col :xl="12">
            <d1-data-type1>  </d1-data-type1>
          </a-col>  
            
        </a-row>
        <!-- 图形-->
       <!-- <a-row :gutter="24">
         <a-col :xl="12">
           <d1-data-type1-chart>  </d1-data-type1-chart>
         </a-col>
         <a-col :xl="12">
           <d1-data-type1-chart>  </d1-data-type1-chart>
         </a-col>  
           
        </a-row>       --> 
        
        <div style="background: #fff;padding-right:16px;height: 100%; margin-top: 5px">
        
          <img style="width: 100%;" :src="currentImgPath"  >
        </div>
            
        
      </a-card>

 
     </a-col>
  </a-row>
      
      

</template>
<script>
import  {DEVICE_TYPE,DEVICE_DATA_TYPE}  from '@/store/devicedata'
//import pick from 'lodash.pick';
import { queryDepartTreeList, queryDeviceList,queryDeviceDataList,queryDeviceDataListByDevice } from '@/api/api';
//import { httpAction, deleteAction } from '@/api/manage';
//import { getImgView } from '@/mixins/JeecgListMixin';
import LineChartMultid from '@comp/chart/LineChartMultid'
import D1DataType1 from './modules/D1DataType1.vue'
//import D1DataType1Chart from './modules/D1DataType1Chart.vue'
export default {
  name: 'DeviceMonitor',
  //mixins:[JeecgListMixin],
  components: {
    LineChartMultid,
    D1DataType1,
    //D1DataType1Chart,
  },
  
  //{ "type": "Feb", "a": 6.9, "jeebt": 4.2 },
  data() {
    return {
      ////缸内温度	1
      tableDataSource1:[], 
      chartTitle1: '缸内温度实时监测图形',
       
      chartDataSource1:[
        { "type": "03-24", "1T": 6.9, "2T": 4.2, "3T": 4.9, "4T": 5.2 },
        { "type": "03-25", "1T": 4.9, "2T": 5.2, "3T": 7.9, "4T": 4.4 },
        { "type": "03-26", "1T": 5.9, "2T": 6.2, "3T": 6.9, "4T": 4.2 },
      ],  ////缸内温度	1
      dataSource3:[], //缸头振动	3
      dataSource4: [], //活塞杆沉降位移	4
      dataSource6: [], //曲轴箱振动	6
      chartFields1: [],
      
      iExpandedKeys: [],
      datanums: 0,
      timer: null, //定时器
      loading: false,
      autoExpandParent: true,
      //currFlowId: '',
     //currFlowName: '',
      disable: true,
      //treeData: [],
      devicetype:[],
      deviceData: [],
      visible: false,
      departTree: [],
      currentImgPath: '',
      rightClickSelectedKey: '',
      hiding: true,
      model: {},
      dropTrigger: '',
      depart: {},
      disableSubmit: false,
      selectedKeys: [],
      autoIncr: 1,
      currSelected: {},

      allTreeKeys: [],

      form: this.$form.createForm(this),
      
      /* graphDatasource: {
        nodes: [],
        edges: []
      }, */
      
      url: {
        delete: '/sys/sysDepart/delete',
        edit: '/sys/sysDepart/edit',
        deleteBatch: '/sys/sysDepart/deleteBatch',
        exportXlsUrl: 'sys/sysDepart/exportXls',
        importExcelUrl: 'sys/sysDepart/importExcel'
      }
    };
  },
  
  methods: {
    //遍历机构Json数据，如果是机构类型就添加图标
    orgTreeJson(jsonObj) {
     //console.log("devicelength: ", this.deviceData.length);
      // 循环所有键
      for (var v in jsonObj) {
        var element = jsonObj[v];
        // 1.判断是否是组织机构，添加图标，以便区别部门
        if (typeof element == 'object') {
          if (element != null) {
            if ('orgCategory' in element) {
              if (element.orgCategory == 1) {  //1 公司机构
                element.slots = { icon: 'org' };
              } else {                         //2 部门 
                element.slots = { icon: 'dep' };
              }
              element.selectable = false;
              //循环查找该部门下的所有设备
              for (let deviceItem of  this.deviceData ){
                if (deviceItem.depatmentId===element.key){//将设备挂在该部门下面
                  element.isLeaf = false;
                  element.children = element.children || [];
                  element.children.push(deviceItem);  
                }
               
              }  
            }
            this.orgTreeJson(element);
          }
        }
      }
    },
    loadData() {
      this.refresh();
   
    },
    getImgView(text){
      if(text && text.indexOf(",")>0){
        text = text.substring(0,text.indexOf(","))
      }
      return window._CONFIG['staticDomainURL']+"/"+text
    },
    LoadDevice(){
      //var that = this;
      queryDeviceList().then(res => {
        if (res.success) {
          for (let i = 0; i < res.result.length; i++) {
            let temp = res.result[i];
            temp.slots = temp.slots || [];
            temp.slots = { icon: 'stop' };
            temp['key'] = temp.id;
            temp['value'] = temp.id;
            temp['title'] = temp.deviceName;
            
            //console.log("deviceImage:", temp['deviceImage'] );
            if (temp['deviceImage']!==null && temp['deviceImage'] !== undefined && temp['deviceImage'] !== ''){
              temp['imagePath'] = this.getImgView(temp['deviceImage']);
              //console.log("imagePath:  ", temp['imagePath']);
            } else{
              //console.log("deviceImage is null" );
              temp['imagePath'] = null;
            }
            
            temp['isLeaf'] = true;
            temp['selectable'] = true;
            this.deviceData.push(temp);
            //that.setThisExpandedKeys(temp);
          }
          console.log("deviceData:  ", this.deviceData);
        }
      });
      this.$nextTick(function () {
      });
    },
    loadTree() {
      var that = this;
      that.treeData = [];
      that.departTree = [];
      //that.deviceData = [];
      //this.LoadDevice();
      //console.log("loadTree mid.. /deviceData.length:  ",this.deviceData.length);
          queryDepartTreeList().then(res => {
            if (res.success) {
              //let deviceData = that.deviceData;
              this.orgTreeJson(res.result);
              for (let i = 0; i < res.result.length; i++) {
                let temp = res.result[i];
     
                that.treeData.push(temp);
                that.departTree.push(temp);
                that.setThisExpandedKeys(temp);
                that.getAllKeys(temp);
              }

              
            }
          });
          this.$nextTick(function () {
          });
          this.loading = false;
      
      
    },
    setThisExpandedKeys(node) {
      if (node.children && node.children.length > 0) {
        this.iExpandedKeys.push(node.key);
        for (let a = 0; a < node.children.length; a++) {
          this.setThisExpandedKeys(node.children[a]);
        }
      }
    },
    refresh() {
      this.loading = true;
      this.LoadDevice();
      this.loadTree();
      
    },
   
    onExpand(expandedKeys) {
      console.log('onExpand', expandedKeys);
      // if not set autoExpandParent to false, if children expanded, parent can not collapse.
      // or, you can remove all expanded children keys.
      this.iExpandedKeys = expandedKeys;
      this.autoExpandParent = false;
    },
    backFlowList() {
      this.$router.back(-1);
    },
    // 右键点击下拉框改变事件
    dropStatus(visible) {
      if (visible == false) {
        this.dropTrigger = '';
      }
    },
    initDataByDevice(deviceid){
      this.tableDataSource1 = [];
      if (this.timer) {
        window.clearInterval(this.timer);
        this.timer = null;
        
      } 
      queryDeviceDataListByDevice({deviceid:deviceid}).then(res => {
        if (res.success) {
          let deviceDatas = res.result;
          this.datanums =deviceDatas.length;
          for (let dataItem of deviceDatas) {
            if (dataItem.dataTypeId==DEVICE_DATA_TYPE.cylinder_temperature){ //缸内温度
              
              this.tableDataSource1.push(dataItem);   
              this.chartFields1.push(dataItem.dataName);
            }
                 
          
          } 
          
        }
      });
      this.$nextTick(function () {
      });
      
    },
    getDataByDevice(deviceid){
      if (this.datanums) {
        queryDeviceDataListByDevice({deviceid:deviceid}).then(res => {
          if (res.success) {
            let deviceDatas = res.result;
            console.log('flush device data');
            for (let dataItem of deviceDatas) {
              if (dataItem.dataTypeId==DEVICE_DATA_TYPE.cylinder_temperature){
                for (let ds of this.tableDataSource1 ) {
                  if (ds.id === dataItem.id) {  //更新当前数据点的数据值
                    ds.crnValue = dataItem.crnValue; 
                    ds.status = dataItem.status;
                  }
                  
                }
                  
              }
                   
            
            }
            
          }
        });
      } else {
        if (this.timer) {
          window.clearInterval(this.timer);
          this.timer = null;
          
        }
        
      }
      
    },
    onSelect(selectedKeys, e) {
         
      this.hiding = false;
      let record = e.node.dataRef;
      //console.log('onSelect-record', record);
      record.slots={ icon: 'normal' };
      this.currSelected = Object.assign({}, record);
      this.model = this.currSelected;
      this.selectedKeys = [record.key];
      this.model.parentId = record.parentId;
      this.currentImgPath = record.imagePath;
      // console.log('onSelect-record', record,this.currentImgPath);
      
      this.initDataByDevice(record.deviceId);
      this.timer = window.setInterval(() => { this.getDataByDevice(record.deviceId)}, 1000)
        
    },
    
    getCurrSelectedTitle() {
      return !this.currSelected.title ? '' : this.currSelected.title;
    },
    onClearSelected() {
      this.hiding = true;
      this.currSelected = {};
      //this.form.resetFields();
      this.selectedKeys = [];
      this.tableDataSource1 = [];
      if (this.timer) {
        window.clearInterval(this.timer);
        this.timer = null;
        
      }
      
      //this.$refs.departAuth.departId = '';
    },
    selectDirectiveOk(record) {
      console.log('选中指令数据', record);
      this.nodeSettingForm.setFieldsValue({ directiveCode: record.directiveCode });
      this.currSelected.sysCode = record.sysCode;
    },
    expandAll() {
      this.iExpandedKeys = this.allTreeKeys;
    },
    closeAll() {
      this.iExpandedKeys = [];
    },

    getAllKeys(node) {
      // console.log('node',node);
      this.allTreeKeys.push(node.key);
      if (node.children && node.children.length > 0) {
        for (let a = 0; a < node.children.length; a++) {
          this.getAllKeys(node.children[a]);
        }
      }
    }
    // <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
  },
  created() {
    //this.currFlowId = this.$route.params.id;
    //this.currFlowName = this.$route.params.name;
    this.LoadDevice();
    this.loadTree();
    
  },
  mounted() {
 
  },
  beforeDestroy() {
    if (this.timer) {
      window.clearInterval(this.timer);        
      this.timer = null;
    }

  }

};
</script>
<style scoped>
.ant-card-body .table-operator {
  margin: 5px;
}

.anty-node-layout .ant-layout-header {
  padding-right: 0;
}

.header {
  padding: 0 8px;
}

.header button {
  margin: 0 3px;
}

.ant-modal-cust-warp {
  height: 100%;
}

.ant-modal-cust-warp .ant-modal-body {
  height: calc(100% - 110px) !important;
  overflow-y: auto;
}

.ant-modal-cust-warp .ant-modal-content {
  height: 90% !important;
  overflow-y: hidden;
}

#app .desktop {
  height: auto !important;
}

/** Button按钮间距 */
.ant-btn {
  margin-left: 3px;
}

.drawer-bootom-button {
  /*position: absolute;*/
  bottom: 0;
  width: 100%;
  border-top: 1px solid #e8e8e8;
  padding: 10px 16px;
  text-align: left;
  left: 0;
  background: #fff;
  border-radius: 0 0 2px 2px;
}
</style>
