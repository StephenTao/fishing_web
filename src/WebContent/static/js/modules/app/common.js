define(['angular', 'angular-ui-router', 'controllers/app'], function(angular) {
  var commonModule = angular.module('commonModule', ['controller', 'ui.router'], angular.noop);

  commonModule.run(function($rootScope, $state, $stateParams) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
  });
   
  commonModule.filter('getMinute',function(){
    return function(timeStamp){
        return Math.floor(timeStamp/60);
    };
  }); 

  commonModule.filter('getSecond',function(){
    return function(timeStamp){
        return  timeStamp%60;
    };
  });

  commonModule.factory('localize',['$http','$window', '$filter', function($http,$window, $filter){ 
      var localize = { 
      language:$window.navigator.userLanguage || $window.navigator.language,
      dictionary:[], 
      resourceFileLoaded:false, 
      arrDynamic : [], 
      successCallback:function (data) { 
       localize.dictionary = data; 
       localize.resourceFileLoaded = true; 
      }, 
     initLocalizedResources:function () {
       var language = localize.language;
       var url = 'js/i18n/resource-locale_' + language.toUpperCase( ) + '.js' ;
       $http({ method:"GET", url:url, cache:false }).success(localize.successCallback).error(function () { 
       var url = 'js/i18n/resource-locale_EN-US.js'; 
       $http({ method:"GET", url:url, cache:false }).success(localize.successCallback); }); 
     },
     getLocalizedString:function (key) {
      var result = '';
      if (!localize.resourceFileLoaded) {
        localize.initLocalizedResources();
        localize.resourceFileLoaded = true;
        return result;
      }
      if ((localize.dictionary !== []) && (localize.dictionary.length > 0)) {
       var entry = $filter('filter')(localize.dictionary, {'key':key})[0];
       if ((entry !== null) && (entry !== undefined)) { 
         result = entry.value;
       } 
      }
     return result;
   }
    };
  return localize;
  }]);

  commonModule.filter("i18n", ['localize', function(localize) {
    return function (input) {
      return localize.getLocalizedString(input);
    };
  }]);

  commonModule.filter('getFileSizeMB',function(){
    return function(number){
      var size = number / 1024 / 1024;
      return size;
    };
  });

  commonModule.filter('getFileSizeKB',function(){
    return function(number){
      var size = number / 1024;
      return size;
    };
  });

  return commonModule;
});