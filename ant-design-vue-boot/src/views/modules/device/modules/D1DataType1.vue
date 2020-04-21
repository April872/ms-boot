<template>
  <a-table 
    :defaultExpandAllRows="true"
    rowKey="id" 
    :columns="columns" 
    :dataSource="data" 
    :pagination="false"
    bordered >
    
    <template slot="title" slot-scope="currentPageData">
      <span style="color:navy">缸内温度监测</span>
    </template>
    <!-- 字符串超长截取省略号显示 -->
    <span slot="status" slot-scope="text, index">
      <!-- <j-ellipsis :value="index.causes" :length="20" /> -->
      <a-tooltip placement="topLeft" > 
        <template slot="title">
           {{index.causes}}
        </template>
        {{text}} 
      </a-tooltip>
     
      </span>
  </a-table>
</template>

<script>
import JEllipsis from '@/components/jeecg/JEllipsis';
export default {
  //往复式压缩机 1  ---  //缸内温度	1
  name: "D1DataType1",
  components: {
    JEllipsis,
  },
  props:{
    dataSource: {
      type:Array,
      default:() => [],
    },
    
  },
  data() {
    return {

      columns: [
          {
            title: '名称',
            align:"center",
            dataIndex: 'dataName',

          },
          {
            title:'描述',
            align:"center",
            dataIndex: 'mark',
          },
          {
            title: '当前值',
            align:"center",
            dataIndex: 'crnValue',
            scopedSlots: { customRender: 'status' },
          },
          {
            title: '状态',
            align:"center",
            dataIndex: 'status',
           
            customRender: (text) => {
              switch (text) {
                case 0:
                  return <span style="color:green;">正常</span>
                case 1:
                  return <span style="color:red;">告警</span>
                default:
                  return text
              }
            },
          },
        ],
        data: this.dataSource,
       
      }
  },
  watch: {
    dataSource(newVal) {
      if (newVal.length === 0) {
        this.data = null;
      } else {
        this.data = newVal;
      }
    }
  },
}
</script>

<style>
.ant-table-title{
 padding: 4px 0;
}
.ant-table-thead > tr > th, .ant-table-tbody > tr > td {
  padding: 5px 5px;
}
.ant-table-tbody .ant-table-row td{
 padding-top: 5px;
 padding-bottom: 5px;
}
  
  
</style>
