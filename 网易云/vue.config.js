module.exports={
    devServer:{
        proxy:{
            '/Music':{
                target:'http://localhost:8099/Music',
                changeOrigin:true,
                pathRewrite: {
                    '^/Music': ''
                }
            },
            '/api':{
                target:'https://music-api.heheda.top',
                changeOrigin:true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    publicPath:'./' 
}