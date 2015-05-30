'use strict';

var canvas = document.getElementById('canvas');//canvas画布
var context = canvas.getContext('2d');//绘图环境

var allSize = 1000;//内存总容量
var usedSize = 0;//已使用容量
var counter = 0;//执行任务计数器

var model = '首次适应算法';//默认模式为首次适应算法

var tasks = [];//建立一个任务队列，绘图函数执行任务队列
var deleteTasks = [];//删除任务的队列

function println(str) {
	var text = $('#monitor');
	console.log(text);
	console.log(text.val(text.val() + str + '\n'));

}

//矩形绘制函数
function draw(needSize) {
	//绘制矩形
	context.beginPath();
	context.moveTo(usedSize, 0);
	context.lineTo(usedSize, 100);
	context.lineTo(usedSize + needSize, 100);
	context.lineTo(usedSize + needSize, 0);
	context.closePath();

	context.lineWidth = 1;
	context.strokeStyle = 'black';
	context.stroke();

	//矩形填充
	context.fillStyle = 'red';
	context.fill();

}

function deDraw(start, needSize) {

	//绘制矩形
	context.beginPath();
	context.moveTo(start, 0);
	context.lineTo(start, 100);
	context.lineTo(start + needSize, 100);
	context.lineTo(start + needSize, 0);
	context.closePath();

	context.lineWidth = 1;
	context.strokeStyle = 'white';
	context.stroke();

	//矩形填充
	context.fillStyle = 'white';
	context.fill();

}

window.onload = function(){

	canvas.width = 1000;
	canvas.height = 100;

};


//得到选择的算法
function getSelector() {
	var selector = document.getElementById('algorithm');
	console.log(selector.value);
	console.log(model);
	if (selector.value != model) {
		//清空canvas
		deDraw(0, 1000);
		usedSize = 0;
	}
}


//根据输入分配内存
function executor() {
	//先判断运行模式
	getSelector();

	//根据input的值分配内存
	var input = malloc.input.value;
	var size = parseInt(input);
	if (usedSize + size > allSize) {
		alert('内存不足，无法分配');
		return;
	} else {
		//绘制内存图
		draw(size);
		usedSize += size;
		tasks.push(size);
		counter++;
		console.log(tasks);

		var remainder = allSize - usedSize;

		//输出内存状态
		println('内存使用' + size + 'KB');
		println('内存剩余' + remainder + 'KB');

	}

}

//根据输入删除进程
function deleteProcess() {
	//根据input删除process
	var input = deleteP.input.value;
	var index = parseInt(input) - 1;

	var temp = tasks[index];
	if (undefined === temp || 0 === temp) {
		alert('进程不存在,请重新输入');
		return;
	} else {
		var start = 0;
		for (var i = 0; i < index; i++) {
			start += tasks[i];
		}
		for (var i = 0; i < deleteTasks.length; i++) {
			start += deleteTasks[i];
		}
		//释放内存
		deDraw(start, tasks[index]);

		deleteTasks.push(tasks[index]);
		tasks[index] = 0;

	}
	console.log(temp);
	console.log(tasks);


}

