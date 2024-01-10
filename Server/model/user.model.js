const db = require('./db')
const userSchema = new db.mongoose.Schema({
    username: { type: String, required: true },
    passwd: { type: String, required: true },
    email: { type: String, required: true },
    fullname: { type: String, required: true }
}, {collection: 'nguoidung'})
let userModel = db.mongoose.model('userModel', userSchema)
module.exports = {userModel}