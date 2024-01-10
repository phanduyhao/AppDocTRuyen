const myModel = require('../model/user.model')
const bcrypt = require('bcrypt')
var socket = require('../socket_server')
exports.registerUser = async (req, res, next) => {
    try {
        let { username, passwd, email, fullname } = req.body
        if (!username || !passwd || !email || !fullname) {
            return res.send({ err: 'Không được để trống dữ liệu' })
        }
        let userAvailable = await myModel.userModel.findOne({ username })
        if (userAvailable) {
            return res.send({ err: 'Tên người dùng đã tồn tại' })
        }
        let salt = await bcrypt.genSalt(10)
        let passwdHash = await bcrypt.hash(passwd, salt)
        await myModel.userModel.create({ username, passwd: passwdHash, email, fullname })
        // socket.io.emit("msgRes","Register Successfully!")
        res.send({ username, passwd, email, fullname })
    } catch (error) {
        res.status(500).render('error', { err: error.message })
    }
}
exports.loginUser = async (req, res, next) => {
    try {
        let { username, passwd } = req.params

        let user = await myModel.userModel.findOne({ username })
        if (!user) {
            res.send({ err: 'Tài khoản không tồn tại' })
        } else {
            let userCheckPass = await bcrypt.compare(passwd, user.passwd)

            userCheckPass ? res.send(user) : res.send({ err: 'Mật khẩu không đúng' })
        }
    } catch (error) {
        res.status(500).render('error', { err: error.message })
    }
}
exports.updateUser = async (req, res, next) => {
    let { _id, username, passwd, email, fullname } = req.body
    await myModel.userModel.updateOne({ _id }, { $set: { username, passwd, email, fullname } })
    res.send("Sua thanh cong")
}
exports.deleteUser = async (req, res, next) => {
    try {
        let { _id } = req.query
        let user = await myModel.userModel.findOne({ _id })
        if (!user) {
            return res.send('Không tìm thấy nguoi dung')
        }
        await myModel.userModel.deleteOne({ _id })
        res.send('Xoa thanh cong')
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}