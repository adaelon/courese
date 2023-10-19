<template>
  <el-dialog
    :title="!dataForm.ratingId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="courseId">
      <el-input v-model="dataForm.courseId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="raterId">
      <el-input v-model="dataForm.raterId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ratingValue">
      <el-input v-model="dataForm.ratingValue" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="feedback">
      <el-input v-model="dataForm.feedback" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ratingDate">
      <el-input v-model="dataForm.ratingDate" placeholder=""></el-input>
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
          ratingId: 0,
          courseId: '',
          raterId: '',
          ratingValue: '',
          feedback: '',
          ratingDate: ''
        },
        dataRule: {
          courseId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          raterId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ratingValue: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          feedback: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ratingDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.ratingId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.ratingId) {
            this.$http({
              url: this.$http.adornUrl(`/score/courseratings/info/${this.dataForm.ratingId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.courseId = data.courseRatings.courseId
                this.dataForm.raterId = data.courseRatings.raterId
                this.dataForm.ratingValue = data.courseRatings.ratingValue
                this.dataForm.feedback = data.courseRatings.feedback
                this.dataForm.ratingDate = data.courseRatings.ratingDate
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
              url: this.$http.adornUrl(`/score/courseratings/${!this.dataForm.ratingId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'ratingId': this.dataForm.ratingId || undefined,
                'courseId': this.dataForm.courseId,
                'raterId': this.dataForm.raterId,
                'ratingValue': this.dataForm.ratingValue,
                'feedback': this.dataForm.feedback,
                'ratingDate': this.dataForm.ratingDate
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
