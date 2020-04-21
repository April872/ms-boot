<template>
  <div :style="{ padding: '0 0 0px 0px' }">
    <line-chart-multid :fields="fields1" :dataSource="dataSource1" :title="title" :height="height"></line-chart-multid>
  </div>
</template>

<script>
  //import { DataSet } from '@antv/data-set'
  import { ChartEventMixins } from '@/components/chart/mixins/ChartMixins.js'

  
  
  export default {
    name: 'D1DataType1Chart',
    mixins: [ChartEventMixins],
    props: {
      title: {
        type: String,
        default: ''
      },
      dataSource: {
        type: Array,
        default: () => [
          { type: 'Jan', a: 7.0, b: 3.9 },
          { type: 'Feb', a: 6.9, b: 4.2 },
          { type: 'Mar', a: 9.5, b: 5.7 },
          { type: 'Apr', a: 14.5, b: 8.5 },
          { type: 'May', a: 18.4, b: 11.9 },
          { type: 'Jun', a: 21.5, b: 15.2 },
          { type: 'Jul', a: 25.2, b: 17.0 },
          { type: 'Aug', a: 26.5, b: 16.6 },
          { type: 'Sep', a: 23.3, b: 14.2 },
          { type: 'Oct', a: 18.3, b: 10.3 },
          { type: 'Nov', a: 13.9, b: 6.6 },
          { type: 'Dec', a: 9.6, b: 4.8 }
        ]
      },
      fields: {
        type: Array,
        default: () => ['a', 'b']
      },
      // 别名，需要的格式：[{field:'name',alias:'姓名'}, {field:'sex',alias:'性别'}]
      aliases:{
        type: Array,
        default: () => []
      },
      height: {
        type: Number,
        default: 254
      }
    },
    data() {
      return {
        fields1:[],
        dataSource1:[],
      }
    },

  }
</script>

<style scoped>

</style>