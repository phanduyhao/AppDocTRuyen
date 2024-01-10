const cmtModel = require('../model/comment.model')
exports.addComment = async (req, res, next) => {
    try {
        let { id_book, id_user, content, commenttime } = req.body
        await cmtModel.cmtModel.create({ id_book, id_user, content, commenttime })
        res.send({id_book, id_user, content, commenttime})
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}
exports.listComments = async (req, res, next) => {
    try {
        let { _id } = req.query
        let commentlist = await cmtModel.cmtModel.find({ id_book: _id }, { id_book: 0, _id: 0 }).populate('id_user', 'fullname')
        res.send(commentlist)
    } catch (error) {
        res.status(500).render('error', { message: error.message })
    }
}