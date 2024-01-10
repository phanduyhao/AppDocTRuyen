const myModel = require('../model/books.model')
const socket = require('../socket_server')
exports.listBooks = async (req, res, next) => {
    let { _id, read } = req.query
    read = (read === 'true')
    if (_id && read) {
        let readbook = await myModel.bookModel.findOne({ _id }, { contentbook: 1 })
        return res.send(readbook)
    } else if (_id && !read) {
        let bookDetail = await myModel.bookModel.findOne({ _id }, { contentbook: 0 })
        return res.send(bookDetail)
    }
    let listbooks = await myModel.bookModel.find({}, { _id: 1, bookname: 1, imgbook: 1 })
    res.send(listbooks)
}
exports.addBook = async (req, res, next) => {
    try {
        let { bookname, description, author, publishyear, imgbook, contentbook } = req.body
        if (!bookname || !description || !author || !publishyear || !imgbook || contentbook.length == 0) {
            return res.send('Không được để trống dữ liệu')
        }
        let newBook = new myModel.bookModel({ bookname, description, author, publishyear, imgbook, contentbook: JSON.parse(contentbook) })
        await newBook.save()
        socket.io.emit("msgCmt","Có truyện mới. Xem ngay!")
        res.send(newBook)
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}
exports.updateBook = async (req, res, next) => {
    try {
        let { _id } = req.query
        let { bookname, description, author, publishyear, imgbook, contentbook } = req.body
        let checkbook = await myModel.bookModel.findOne({ _id })
        if (!checkbook) {
            return res.send('Không tìm thấy sách')
        }
        await myModel.bookModel.updateOne({ _id }, { $set: { bookname, description, author, publishyear, imgbook, contentbook: JSON.parse(contentbook) } })
        let returnBook = await myModel.bookModel.findOne({ _id })
        res.send(returnBook)
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}

exports.deleteBook = async (req, res, next) => {
    try {
        let { _id } = req.query
        let checkbook = await myModel.bookModel.findOne({ _id })
        if (!checkbook) {
            return res.send('Không tìm thấy sách')
        }
        await myModel.bookModel.deleteOne({ _id })
        res.send('Xoa thanh cong')
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}

exports.filterBooks = async (req, res, next) => {
    try {
        let { bookname } = req.query
        let listBookFilter = await myModel.bookModel.find({ bookname: new RegExp(bookname, 'i') }, { _id: 1, bookname: 1, imgbook: 1 })
        res.send(listBookFilter)
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}