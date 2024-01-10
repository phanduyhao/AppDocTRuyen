const io = require('socket.io')()
const socketapi = {
    io: io
}
io.on("connection", (client) => {
    client.on('msgRes', (data) => {
        client.emit('msgRes', `Đăng ký tài khoản ${data} thành công`)
    })

    client.on('msgCmt', (data) => {
        io.emit('msgCmt', `Truyện ${JSON.parse(data).bookname} có bình luận mới: ${JSON.parse(data).content}`)
    })

    client.on('disconnect', () => {
        console.log('Client disconnected!');
    })
})

module.exports = socketapi