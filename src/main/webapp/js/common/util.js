Util = function(){
    /**
     * 判断是否为空
     * param str 字符串
     * return boolean true为空
     */
    this.isNull = function (str) {
        //如果str为boolean类型，将不支持后面的replace方法。
        if(typeof (str) === "boolean"){
            return false;
        }
        return (null === str || typeof (str) === "undefined" || str === "undefined" || ( typeof (str) === "string" && str.replace(/(^\s*)|(\s*$)/g, "") === ""));
    };

    /**
     * 添加方法 增加代码的可读性
     */
    this.isNotNull = function (str) {
        return !this.isNull(str);
    };
};