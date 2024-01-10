var express = require('express');
var router = express.Router();
var cmtsCtrl = require('../controller/comment.controller')
router.get('/',cmtsCtrl.listComments);
router.post('/cmt',cmtsCtrl.addComment);

module.exports = router;
