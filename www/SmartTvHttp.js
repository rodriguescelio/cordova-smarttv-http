var exec = require('cordova/exec');

var SmartTvHttp = {
    
    get: function(url, header, success, error) {
        return exec(success, error, 'SmartTvHttp', 'get', [url, header]);
    },

    post: function(url, body, header, success, error) {
        return exec(success, error, 'SmartTvHttp', 'post', [url, body, header]);
    }

}

module.exports = SmartTvHttp;
window.SmartTvHttp = SmartTvHttp;