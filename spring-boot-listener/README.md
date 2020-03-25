
### 1.监听器
监听器是一个对象，这个对象专门用来监视另一个对象的状态或者行为是否发生变化，且当被监听对象发生变化时，监听器执行相应的逻辑操作。



### 2.ServletRequestListener
ServletRequestListener 是个接口，这个接口是专门用来监听请求的，里面有两个方法：
`requestInitialized`: 请求创建
`requestDestroyed`: 请求销毁。

### 3.demo介绍
demo中使用`ServletRequestListener` 监听Hello.hello方法的执行。