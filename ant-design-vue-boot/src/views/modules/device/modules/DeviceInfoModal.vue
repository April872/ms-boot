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

        <a-form-item label="设备编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'deviceId', validatorRules.deviceId]" placeholder="请输入设备编号"></a-input>
        </a-form-item>
        <a-form-item label="设备名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'deviceName', validatorRules.deviceName]" placeholder="请输入设备名称"></a-input>
        </a-form-item>
        <a-form-item label="所在位置" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'location', validatorRules.location]" placeholder="请输入所在位置"></a-input>
        </a-form-item>
        <a-form-item label="设备类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['deviceType', validatorRules.deviceType]" :trigger-change="true" dictCode="device_type" placeholder="请选择设备类型"/>
        </a-form-item>
        <a-form-item label="设备图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-upload v-decorator="['deviceImage', validatorRules.deviceImage]" :trigger-change="true"></j-upload>
        </a-form-item>
        <a-form-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-select-depart v-decorator="['depatmentId', validatorRules.depatmentId]" :trigger-change="true"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mark', validatorRules.mark]" placeholder="请输入备注"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JUpload from '@/components/jeecg/JUpload'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: "DeviceInfoModal",
    components: { 
      JUpload,
      JSelectDepart,
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
          deviceId: {rules: [
            {required: true, message: '请输入设备编号!'},
          ]},
          deviceName: {rules: [
            {required: true, message: '请输入设备名称!'},
          ]},
          location: {rules: [
          ]},
          deviceType: {rules: [
            {required: true, message: '请输入设备类型!'},
          ]},
          deviceImage: {rules: [
          ]},
          depatmentId: {rules: [
            {required: true, message: '请输入所属部门!'},
          ]},
          mark: {rules: [
          ]},
        },
        url: {
          add: "/device/deviceInfo/add",
          edit: "/device/deviceInfo/edit",
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
          this.form.setFieldsValue(pick(this.model,'deviceId','deviceName','location','deviceType','deviceImage','depatmentId','mark','createBy','createTime'))
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
        this.form.setFieldsValue(pick(row,'deviceId','deviceName','location','deviceType','deviceImage','depatmentId','mark','createBy','createTime'))
      },

      
    }
  }
</script>