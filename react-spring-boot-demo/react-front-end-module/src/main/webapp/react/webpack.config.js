var webpack = require('webpack');


module.exports = {
    entry: "./main.tsx",
    output: {
        path: __dirname + '/../../resources/public/js/',
        publicPath: "js/",
        filename: "bundle.js"
    },
    resolve: {
	    extensions: [".ts", ".tsx", ".js", ".json", ".jsx"]
    },
    module: {
        loaders: [
            { test: /\.tsx?$/, loader: "awesome-typescript-loader" },
            {
                test: /\.js$/,
                loader: "babel-loader",
                exclude: [/node_modules/, /public/],
	            query: {presets: ['env']}
            },
            {
                test: /\.jsx$/,
                loader: "babel-loader",
                exclude: [/node_modules/, /public/],
                query: {presets: ['es2015']}
            },
            {
                test: /\.json$/, loader: "json-loader"
            },
            {
                test: /\.less$/,
                loader: "style-loader!css-loader!less-loader",
                exclude: [/node_modules/, /public/]
            }
        ]
    }
}