const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');



module.exports = {
  entry: path.resolve(__dirname, 'src', 'index.js'),
  output: {
    path: path.resolve(__dirname, 'dist'),
    clean: true,
    filename: 'app.js',
  },
  plugins: [
    new HtmlWebpackPlugin({
        template: path.resolve(__dirname, 'src', 'index.html'),
        filename: 'index.html'
    }),
    new CopyWebpackPlugin({
        patterns: [
          {
            from: path.resolve(__dirname,  'src', 'style.css'),
            to: path.resolve(__dirname,  'dist', 'css'),
          },
        ],
    }),
  ]
};