
var exec = require('cordova/exec');

var PLUGIN_NAME = 'DmpPlugin';

var DmpPlugin = {
  echo: function (phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  fetchDMP: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchDMP', []);
  },
  initFlurry: (flurryId, cb) => {
    exec(cb, null, PLUGIN_NAME, 'initFlurry', [flurryId]);
  },
  logAppSessionEvent: (appId, cb) => {
    exec(cb, null, PLUGIN_NAME, 'logAppSessionEvent', [appId]);
  },
  deleteSharedPrefs: ( cb) => {
    exec(cb, null, PLUGIN_NAME, 'deleteSharedPrefs', []);
  }
};

module.exports = DmpPlugin;
