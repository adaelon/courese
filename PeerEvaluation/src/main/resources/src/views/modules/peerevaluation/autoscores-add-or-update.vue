<template>
  <el-dialog
    :title="!dataForm.autoScoreId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="submissionId">
      <el-input v-model="dataForm.submissionId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="scoreAlgorithm">
      <el-input v-model="dataForm.scoreAlgorithm" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="score">
      <el-input v-model="dataForm.score" placeholder=""></el-input>
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
          autoScoreId: 0,
          submissionId: '',
          scoreAlgorithm: '',
          score: ''
        },
        dataRule: {
          submissionId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          scoreAlgorithm: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.autoScoreId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.autoScoreId) {
            this.$http({
              url: this.$http.adornUrl(`/peerevaluation/autoscores/info/${this.dataForm.autoScoreId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.submissionId = data.autoScores.submissionId
                this.dataForm.scoreAlgorithm = data.autoScores.scoreAlgorithm
                this.dataForm.score = data.autoScores.score
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
              url: this.$http.adornUrl(`/peerevaluation/autoscores/${!this.dataForm.autoScoreId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'autoScoreId': this.dataForm.autoScoreId || undefined,
                'submissionId': this.dataForm.submissionId,
                'scoreAlgorithm': this.dataForm.scoreAlgorithm,
                'score': this.dataForm.score
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
