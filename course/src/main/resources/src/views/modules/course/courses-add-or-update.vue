<template>
  <el-dialog
    :title="!dataForm.courseId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="courseName">
      <el-input v-model="dataForm.courseName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="description">
      <el-input v-model="dataForm.description" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creatorId">
      <el-input v-model="dataForm.creatorId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="creationDate">
      <el-input v-model="dataForm.creationDate" placeholder=""></el-input>
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
          courseId: 0,
          courseName: '',
          description: '',
          creatorId: '',
          categoryId: '',
          creationDate: ''
        },
        dataRule: {
          courseName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creatorId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          creationDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.courseId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.courseId) {
            this.$http({
              url: this.$http.adornUrl(`/course/courses/info/${this.dataForm.courseId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.courseName = data.courses.courseName
                this.dataForm.description = data.courses.description
                this.dataForm.creatorId = data.courses.creatorId
                this.dataForm.categoryId = data.courses.categoryId
                this.dataForm.creationDate = data.courses.creationDate
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
              url: this.$http.adornUrl(`/course/courses/${!this.dataForm.courseId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'courseId': this.dataForm.courseId || undefined,
                'courseName': this.dataForm.courseName,
                'description': this.dataForm.description,
                'creatorId': this.dataForm.creatorId,
                'categoryId': this.dataForm.categoryId,
                'creationDate': this.dataForm.creationDate
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
