const db = require('./db')
const commentSchema = new db.mongoose.Schema({
    id_book: {type: db.mongoose.Schema.Types.ObjectId, ref: 'bookModel'},
    id_user: {type: db.mongoose.Schema.Types.ObjectId, ref: 'userModel'},
    content: { type: String, required: true },
    commenttime: { type: String, required: true }
}, { collection: 'binhluan' })
let cmtModel = db.mongoose.model('cmtModel', commentSchema)
module.exports = { cmtModel }