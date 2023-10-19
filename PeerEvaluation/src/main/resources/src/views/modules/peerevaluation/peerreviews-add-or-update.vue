<template>
  <el-dialog
    :title="!dataForm.reviewId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="submissionId">
      <el-input v-model="dataForm.submissionId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="reviewerId">
      <el-input v-model="dataForm.reviewerId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="score">
      <el-input v-model="dataForm.score" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="feedback">
      <el-input v-model="dataForm.feedback" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="reviewDate">
      <el-input v-model="dataForm.reviewDate" placeholder=""></el-input>
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
          reviewId: 0,
          submissionId: '',
          reviewerId: '',
          score: '',
          feedback: '',
          reviewDate: ''
        },
        dataRule: {
          submissionId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          reviewerId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          feedback: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          reviewDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.reviewId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.reviewId) {
            this.$http({
              url: this.$http.adornUrl(`/peerevaluation/peerreviews/info/${this.dataForm.reviewId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.submissionId = data.peerReviews.submissionId
                this.dataForm.reviewerId = data.peerReviews.reviewerId
                this.dataForm.score = data.peerReviews.score
                this.dataForm.feedback = data.peerReviews.feedback
                this.dataForm.reviewDate = data.peerReviews.reviewDate
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
              url: this.$http.adornUrl(`/peerevaluation/peerreviews/${!this.dataForm.reviewId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'reviewId': this.dataForm.reviewId || undefined,
                'submissionId': this.dataForm.submissionId,
                'reviewerId': this.dataForm.reviewerId,
                'score': this.dataForm.score,
                'feedback': this.dataForm.feedback,
                'reviewDate': this.dataForm.reviewDate
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
