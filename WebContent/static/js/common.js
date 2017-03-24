//获取项目路径
var pathName = window.location.pathname.substring(1);
var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
var basePath= window.location.protocol + '//' + window.location.host + '/' + webName;
console.log(basePath);

