require.config({
  baseUrl: "js",
  urlArgs: "v=1.2.1",
  paths: {
    'jQuery': 'lib/jquery-1.10.2.min',
    'angular': 'lib/angular',
    'angular-resource': 'lib/angular-resource',
    'angular-ui-router': 'lib/angular-ui-router.min',
  },
  shim: {
    'jQuery': {exports: 'jQuery'},
    'angular': {exports: 'angular'},
    'angular-resource': ['angular'],
    'angular-ui-router': ['angular']
  }
});
require(['jQuery', 'angular', 'modules/app/common', 'modules/app/index'] , function ($, angular) {
  angular.bootstrap(document , ['commonModule', 'indexModule']);
});
