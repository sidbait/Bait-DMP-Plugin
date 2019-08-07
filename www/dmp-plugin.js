
var exec = require('cordova/exec');

var PLUGIN_NAME = 'DmpPlugin';

var DmpPlugin = {
  echo: function (phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  getDevice: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'getDevice', []);
  }
};

module.exports = DmpPlugin;
