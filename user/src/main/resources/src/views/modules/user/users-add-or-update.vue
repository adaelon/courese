<template>
  <el-dialog
    :title="!dataForm.userId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="username">
      <el-input v-model="dataForm.username" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="password">
      <el-input v-model="dataForm.password" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="email">
      <el-input v-model="dataForm.email" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="intro">
      <el-input v-model="dataForm.intro" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="profilePicture">
      <el-input v-model="dataForm.profilePicture" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="dateRegistered">
      <el-input v-model="dataForm.dateRegistered" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastLogin">
      <el-input v-model="dataForm.lastLogin" placeholder=""></el-input>
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
          userId: 0,
          username: '',
          password: '',
          email: '',
          intro: '',
          profilePicture: '',
          dateRegistered: '',
          lastLogin: ''
        },
        dataRule: {
          username: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          intro: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          profilePicture: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          dateRegistered: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastLogin: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/user/users/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.users.username
                this.dataForm.password = data.users.password
                this.dataForm.email = data.users.email
                this.dataForm.intro = data.users.intro
                this.dataForm.profilePicture = data.users.profilePicture
                this.dataForm.dateRegistered = data.users.dateRegistered
                this.dataForm.lastLogin = data.users.lastLogin
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
              url: this.$http.adornUrl(`/user/users/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'email': this.dataForm.email,
                'intro': this.dataForm.intro,
                'profilePicture': this.dataForm.profilePicture,
                'dateRegistered': this.dataForm.dateRegistered,
                'lastLogin': this.dataForm.lastLogin
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
