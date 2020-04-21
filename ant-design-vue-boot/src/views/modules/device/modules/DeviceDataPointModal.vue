<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="数据点编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataId', validatorRules.dataId]" placeholder="请输入数据点编号"></a-input>
        </a-form-item>
        <a-form-item label="数据点名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataName', validatorRules.dataName]" placeholder="请输入数据点名称"></a-input>
        </a-form-item>
        <a-form-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mark', validatorRules.mark]" placeholder="请输入描述"></a-input>
        </a-form-item>
        <a-form-item label="所属数据类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['dataTypeId', validatorRules.dataTypeId]" :trigger-change="true" dictCode="data_type" placeholder="请选择所属数据类型"/>
        </a-form-item>
        <a-form-item label="所属设备" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['deviceId', validatorRules.deviceId]" :trigger-change="true" dictCode="device_info,device_name,device_id" placeholder="请选择所属设备"/>
        </a-form-item>
        <a-form-item label="最小阈值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'minValue', validatorRules.minValue]" placeholder="请输入最小阈值"></a-input>
        </a-form-item>
        <a-form-item label="最大阈值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'maxValue', validatorRules.maxValue]" placeholder="请输入最大阈值"></a-input>
        </a-form-item>
        <a-form-item label="单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['unit', validatorRules.unit]" :trigger-change="true" dictCode="unit" placeholder="请选择单位"/>
        </a-form-item>
        <a-form-item label="故障分析" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['causes', validatorRules.causes]" rows="4" autosize placeholder="请输入故障分析"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: "DeviceDataPointModal",
    components: { 
      JDictSelectTag,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          dataId: {rules: [
            {required: true, message: '请输入数据点编号!'},
          ]},
          dataName: {rules: [
            {required: true, message: '请输入数据点名称!'},
          ]},
          mark: {rules: [
          ]},
          dataTypeId: {rules: [
            {required: true, message: '请输入所属数据类型!'},
          ]},
          deviceId: {rules: [
            {required: true, message: '请输入所属设备!'},
          ]},
          minValue: {rules: [
          ]},
          maxValue: {rules: [
          ]},
          unit: {rules: [
          ]},
          causes: {rules: [
          ]},
        },
        url: {
          add: "/device/deviceDataPoint/add",
          edit: "/device/deviceDataPoint/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'dataId','dataName','mark','dataTypeId','deviceId','minValue','maxValue','unit','causes'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'dataId','dataName','mark','dataTypeId','deviceId','minValue','maxValue','unit','causes'))
      },

      
    }
  }
</script>