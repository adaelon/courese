<template>
  <el-dialog
    :title="!dataForm.anomalyId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="reviewerId">
      <el-input v-model="dataForm.reviewerId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="submissionId">
      <el-input v-model="dataForm.submissionId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="anomalyDescription">
      <el-input v-model="dataForm.anomalyDescription" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="detectionDate">
      <el-input v-model="dataForm.detectionDate" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          anomalyId: 0,
          reviewerId: '',
          submissionId: '',
          anomalyDescription: '',
          detectionDate: ''
        },
        dataRule: {
          reviewerId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          submissionId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          anomalyDescription: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          detectionDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.anomalyId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.anomalyId) {
            this.$http({
              url: this.$http.adornUrl(`/peerevaluation/anomalies/info/${this.dataForm.anomalyId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.reviewerId = data.anomalies.reviewerId
                this.dataForm.submissionId = data.anomalies.submissionId
                this.dataForm.anomalyDescription = data.anomalies.anomalyDescription
                this.dataForm.detectionDate = data.anomalies.detectionDate
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/peerevaluation/anomalies/${!this.dataForm.anomalyId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'anomalyId': this.dataForm.anomalyId || undefined,
                'reviewerId': this.dataForm.reviewerId,
                'submissionId': this.dataForm.submissionId,
                'anomalyDescription': this.dataForm.anomalyDescription,
                'detectionDate': this.dataForm.detectionDate
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
