<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="设备名称">
              <a-input placeholder="请输入设备名称" v-model="queryParam.deviceName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="设备类型">
              <j-dict-select-tag placeholder="请选择设备类型" v-model="queryParam.deviceType" dictCode="device_type"/>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="所属部门">
                <a-input placeholder="请输入所属部门" v-model="queryParam.depatmentId"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('device_info')">导出</a-button>
      <a-upload 
        name="file" 
        :showUploadList="false" 
        :multiple="false" 
        :headers="tokenHeader" 
        :action="importExcelUrl" 
        @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>
      <!-- :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}" -->
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        filterMultiple="filterMultiple"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange, type:'radio'}"
        :customRow="clickThenCheck"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <a v-if="text" > 
            <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
            <img
              v-else
              :src="getImgView(text)"
              :preview="getImgView(text)"
              height="25px"
              alt="图片不存在"
              style="max-width:80px;font-size: 12px;font-style: italic;"/>
          </a>
          
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->
    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="数据点信息" key="1">
        <Device-Data-Point-List ref="DeviceDataPointList"></Device-Data-Point-List>
      </a-tab-pane>
      
    </a-tabs>
    <deviceInfo-modal ref="modalForm" @ok="modalFormOk"></deviceInfo-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DeviceInfoModal from './modules/DeviceInfoModal'
  import DeviceDataPointList from './DeviceDataPointList'
  //import DeviceDataPointModal from './modules/DeviceDataPointModal'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  //import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: "DeviceInfoList",
    mixins:[JeecgListMixin],
    components: {
      JDictSelectTag,
      DeviceInfoModal,
      DeviceDataPointList
    },
    data () {
      return {
        description: 'device_info管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'设备编号',
            align:"center",
            dataIndex: 'deviceId',
            defaultSortOrder: 'ascend',
            sorter: true,
            
          },
          {
            title:'设备名称',
            align:"center",
            dataIndex: 'deviceName'
          },
          {
            title:'所在位置',
            align:"center",
            dataIndex: 'location'
          },
          {
            title:'设备类型',
            align:"center",
            dataIndex: 'deviceType_dictText'
          },
          {
            title:'设备图片',
            align:"center",
            dataIndex: 'deviceImage',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title:'所属部门',
            align:"center",
            dataIndex: 'depatmentId_dictText'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'mark'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/device/deviceInfo/list",
          delete: "/device/deviceInfo/delete",
          deleteBatch: "/device/deviceInfo/deleteBatch",
          exportXlsUrl: "/device/deviceInfo/exportXls",
          importExcelUrl: "device/deviceInfo/importExcel",
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
        
        
      },
      
      clickThenCheck(record) {
        return {
          on: {
            click: () => {
              this.onSelectChange(record.id.split(","), [record]);
            }
          }
        };
      },
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        this.$refs.DeviceDataPointList.getDeviceMain(this.selectionRows[0].deviceId);
        //this.$refs.jeecgOrderTicketList.getOrderMain(this.selectedRowKeys[0]);
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.$refs.DeviceDataPointList.queryParam.mainId = null;
        //this.$refs.jeecgOrderTicketList.queryParam.mainId = null;
        this.$refs.DeviceDataPointList.loadData();
        //this.$refs.jeecgOrderTicketList.loadData();
        this.$refs.DeviceDataPointList.selectedRowKeys = [];
        this.$refs.DeviceDataPointList.selectionRows = [];
        //this.$refs.jeecgOrderTicketList.selectedRowKeys = [];
        //this.$refs.jeecgOrderTicketList.selectionRows = [];
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';

  
</style>