const db = require('./db')
const booksSchema = new db.mongoose.Schema({
    bookname: { type: String, required: true },
    description: { type: String, required: true },
    author: { type: String, required: true },
    publishyear: { type: Number, required: true },
    imgbook: { type: String, required: true },
    contentbook: { type: Array, required: true }
}, { collection: 'truyentranh' })
let bookModel = db.mongoose.model('bookModel', booksSchema)
module.exports = { bookModel }