const mongoose = require('mongoose')

mongoose.connect('mongodb://127.0.0.1:27017/truyentranh')
    .catch((err) => {
        console.log(err)
        console.log('Loi ket noi csdl');
    })
module.exports = { mongoose }