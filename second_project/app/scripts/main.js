'use strict';

var canvas = document.getElementById('canvas');//canvas画布
var context = canvas.getContext('2d');//绘图环境

var allSize = 640;//内存总容量
var usedSize = 0;//已使用容量
var counter = 0;//执行任务计数器

var model = '首次适应算法';//默认模式为首次适应算法

var tasks = [];//建立一个任务队列，绘图函数执行任务队列

function println(str) {
	var text = $('#monitor');
	text.val(text.val() + str + '\n');
}

//矩形绘制函数
function draw(start, end) {
	//绘制矩形
	context.beginPath();
	context.moveTo(start, 0);
	context.lineTo(start, 100);
	context.lineTo(end, 100);
	context.lineTo(end, 0);
	context.closePath();

	context.lineWidth = 1;
	context.strokeStyle = 'black';
	context.stroke();

	//矩形填充
	context.fillStyle = 'red';
	context.fill();

}

function deDraw(start, end) {

	//绘制矩形
	context.beginPath();
	context.moveTo(start, 0);
	context.lineTo(start, 100);
	context.lineTo(end, 100);
	context.lineTo(end, 0);
	context.closePath();

	context.lineWidth = 1;
	context.strokeStyle = 'white';
	context.stroke();

	//矩形填充
	context.fillStyle = 'white';
	context.fill();

}

window.onload = function(){

	canvas.width = allSize;
	canvas.height = 100;

};


//得到选择的算法
function selectorIsChanged() {
	var selector = document.getElementById('algorithm');
	//console.log(model);
	if (selector.value !== model) {
		//清空canvas
		deDraw(0, 1000);
		usedSize = 0;

		//清空tasks
		tasks = [];
		counter = 0;
	}
}

//对tasks中的task按照start排序
function sortTask() {
	var temp = {};
	//冒泡排序
	for (var i = 0; i < tasks.length - 1; i++) {
		for (var j = 0; j < tasks.length - 1 - i; j++) {
			if (tasks[j].start > tasks[j+1].start) {
				temp = tasks[j];
				tasks[j] = tasks[j+1];
				tasks[j+1] = temp;
			}
		}
	}
}

//根据输入分配内存
function executor() {
	//存储是否成功
	var isSuccess = false;

	//先判断运行模式
	selectorIsChanged();

	//根据input的值分配内存
	var input = malloc.input.value;
	var size = parseInt(input);
	if (usedSize + size > allSize) {
		alert('内存不足，无法分配');
		return;
	} else {
		var task = {};
		if (undefined === tasks[0]) {
			//第一次写入内存，直接分配
			task.start = usedSize;
			task.size = size;
			usedSize += size;
			task.end = usedSize;

			//任务计数器++
			counter++;

			//绘图
			draw(task.start, task.end);
			tasks.push(task);

			isSuccess = true;
		} else {
			var temp = {};
			//根据不同算法分配内存
			if ('首次适应算法' === model) {
				//遍历tasks找到可以放得下size的区间
				for (var i = 0; i < tasks.length - 1; i++) {
					if (size <= (tasks[i+1].start - tasks[i].end)) {
						task.start = tasks[i].end;
						task.size = tasks[i].size;
						task.end = task.start + task.size;

						//任务计数器++
						counter++;

						//绘图
						draw(task.start, task.end);
						tasks.push(task);
						usedSize += size;

						//对tasks排序
						sortTask();

						isSuccess = true;
						break;
					}
				}
				//如果前面空不够大，考虑memery末尾的内存是否够
				if (!isSuccess) {
					temp = tasks[tasks.length-1];
					var endEmpty = allSize - temp.end;
					if (endEmpty >= size) {
						task.start = temp.end;
						task.size = size;
						task.end = task.start + task.size;

						//任务计数器++
						counter++;

						//绘图
						draw(task.start, task.end);
						tasks.push(task);
						usedSize += size;

						//对tasks排序
						sortTask();

						isSuccess = true;
					}
				}
			} else if ('最佳适应算法' === model) {
				var emptyList = [];
				temp = {};

				//遍历tasks找到可以放得下的size的区间放入emptyList
				for (var i = 0; i < tasks.length - 1; i++) {
					var emptySize = tasks[i+1].start - tasks[i].end;
					if (size <= emptySize) {
						temp.start = tasks[i].end;
						temp.size = size;
						temp.end = temp.start + temp.size;
						emptyList.push(temp);
					}
				}

				if (undefined !== emptyList[0]) {
					//对emptyList从大到小冒泡排序
					for (i = 0; i < emptyList.length - 1; i++) {
						for (var j = 0; j < emptyList.length - 1 - i; j++) {
							if (emptyList[j].size < emptyList[j+1].size) {
								temp = emptyList[j];
								emptyList[j] = emptyList[j+1];
								emptyList[j+1] = temp;
							}
						}
					}
					task = emptyList.pop();

					//任务计数器
					counter++;

					//绘图
					draw(task.start, task.end);
					tasks.push(task);
					usedSize += size;

					//对tasks排序
					sortTask();

					isSuccess = true;

				} else {
					//如果前面空不够大，考虑memery末尾的内存是否够
					temp = tasks[tasks.length-1];
					var endEmpty = allSize - temp.end;
					if (endEmpty >= size) {
						task.start = temp.end;
						task.size = size;
						task.end = task.start + task.size;

						//任务计数器++
						counter++;

						//绘图
						draw(task.start, task.end);
						tasks.push(task);
						usedSize += size;

						//对tasks排序
						sortTask();

						isSuccess = true;
					}
				}

			}
		}

		if (!isSuccess) {
			alert('内存不足，无法分配');
			return;
		}
		var remainder = allSize - usedSize;
		//输出内存状态
		println('正在运行的进程:' + counter + '条');
		println('内存已使用' + usedSize + 'KB');
		println('内存剩余' + remainder + 'KB');
		println('');
		

	}

}

//根据输入删除进程
function deleteProcess() {
	//根据input删除process
	var input = deleteP.input.value;
	var index = parseInt(input) - 1;

	var temp = tasks[index];
	if (undefined === temp) {
		alert('进程不存在,请重新输入');
		return;
	} else {
		for (var i = index; i < tasks.length - 1; i++) {
			tasks[i] = tasks[i+1];
		}
		tasks.pop();
		console.log(temp);

		//释放内存
		deDraw(temp.start, temp.end);
		usedSize -= temp.size;

		//输出
		println('结束进程' + input);
		println('释放内存' + temp.size +'KB');

	}

}

