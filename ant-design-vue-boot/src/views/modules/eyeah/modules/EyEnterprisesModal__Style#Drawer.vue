<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="企业编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'entpId', validatorRules.entpId]" placeholder="请输入企业编号"></a-input>
        </a-form-item>
        <a-form-item label="企业名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'entpName', validatorRules.entpName]" placeholder="请输入企业名称"></a-input>
        </a-form-item>
        <a-form-item label="注册名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'entpNameReg', validatorRules.entpNameReg]" placeholder="请输入注册名称"></a-input>
        </a-form-item>
        <a-form-item label="在用名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'entpNameUse', validatorRules.entpNameUse]" placeholder="请输入在用名称"></a-input>
        </a-form-item>
        <a-form-item label="登记设备点数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'deviceAmountReg', validatorRules.deviceAmountReg]" placeholder="请输入登记设备点数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="在用设备点数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'deviceAmountUse', validatorRules.deviceAmountUse]" placeholder="请输入在用设备点数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="企业税号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'taxNum', validatorRules.taxNum]" placeholder="请输入企业税号"></a-input>
        </a-form-item>
        <a-form-item label="开户行" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'bank', validatorRules.bank]" placeholder="请输入开户行"></a-input>
        </a-form-item>
        <a-form-item label="账号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'account', validatorRules.account]" placeholder="请输入账号"></a-input>
        </a-form-item>
        <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'address', validatorRules.address]" placeholder="请输入地址"></a-input>
        </a-form-item>
        <a-form-item label="邮编" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'postCode', validatorRules.postCode]" placeholder="请输入邮编"></a-input>
        </a-form-item>
        <a-form-item label="电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'phone', validatorRules.phone]" placeholder="请输入电话"></a-input>
        </a-form-item>
        <a-form-item label="短信账号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'smsid', validatorRules.smsid]" placeholder="请输入短信账号"></a-input>
        </a-form-item>
        <a-form-item label="短信密码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'smspwd', validatorRules.smspwd]" placeholder="请输入短信密码"></a-input>
        </a-form-item>
        <a-form-item label="当前短信数目" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'smsAmount', validatorRules.smsAmount]" placeholder="请输入当前短信数目" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="会员数目" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'memberAmount', validatorRules.memberAmount]" placeholder="请输入会员数目" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="账户金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'accontMoney', validatorRules.accontMoney]" placeholder="请输入账户金额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="维保费用" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'serviceFare', validatorRules.serviceFare]" placeholder="请输入维保费用" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="维保期限" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择维保期限" v-decorator="[ 'servicePeriod', validatorRules.servicePeriod]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>
        <a-form-item label="最近登陆时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择最近登陆时间" v-decorator="[ 'latestTime', validatorRules.latestTime]" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建日期" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'updateBy', validatorRules.updateBy]" placeholder="请输入更新人"></a-input>
        </a-form-item>
        <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择更新日期" v-decorator="[ 'updateTime', validatorRules.updateTime]" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "EyEnterprisesModal",
    components: { 
      JDate,
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
          entpId: {rules: [
            {required: true, message: '请输入企业编号!'},
          ]},
          entpName: {rules: [
            {required: true, message: '请输入企业名称!'},
          ]},
          entpNameReg: {rules: [
            {required: true, message: '请输入注册名称!'},
          ]},
          entpNameUse: {rules: [
          ]},
          deviceAmountReg: {rules: [
          ]},
          deviceAmountUse: {rules: [
          ]},
          taxNum: {rules: [
          ]},
          bank: {rules: [
          ]},
          account: {rules: [
          ]},
          address: {rules: [
          ]},
          postCode: {rules: [
          ]},
          phone: {rules: [
          ]},
          smsid: {rules: [
          ]},
          smspwd: {rules: [
          ]},
          smsAmount: {rules: [
          ]},
          memberAmount: {rules: [
          ]},
          accontMoney: {rules: [
          ]},
          serviceFare: {rules: [
          ]},
          servicePeriod: {rules: [
          ]},
          remark: {rules: [
          ]},
          latestTime: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateBy: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/ant/eyEnterprises/add",
          edit: "/ant/eyEnterprises/edit",
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
          this.form.setFieldsValue(pick(this.model,'entpId','entpName','entpNameReg','entpNameUse','deviceAmountReg','deviceAmountUse','taxNum','bank','account','address','postCode','phone','smsid','smspwd','smsAmount','memberAmount','accontMoney','serviceFare','servicePeriod','remark','latestTime','createBy','createTime','updateBy','updateTime'))
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
        this.form.setFieldsValue(pick(row,'entpId','entpName','entpNameReg','entpNameUse','deviceAmountReg','deviceAmountUse','taxNum','bank','account','address','postCode','phone','smsid','smspwd','smsAmount','memberAmount','accontMoney','serviceFare','servicePeriod','remark','latestTime','createBy','createTime','updateBy','updateTime'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>