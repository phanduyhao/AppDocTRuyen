var express = require('express');
var router = express.Router();
var userCtrl = require('../controller/user.controller')
router.get('/login/:username/:passwd', userCtrl.loginUser);
router.post('/register', userCtrl.registerUser);
router.put('/updateuser', userCtrl.updateUser)
module.exports = router;
