var express = require('express');
var router = express.Router();
var booksCtrl = require('../controller/books.controller')
router.get('/', booksCtrl.listBooks);
router.get('/filter', booksCtrl.filterBooks)
router.post('/book', booksCtrl.addBook)
router.put('/book', booksCtrl.updateBook)
router.delete('/book', booksCtrl.deleteBook)
module.exports = router;
