<template>
	<view class="content1">
		<text style="font-size: 25px;font-weight: 800;">请选择搜索标签：</text>
		<view class="content3">
			<picker @change="bindPickerChange1" :range="educationArray">
				<label class="searchway">学历：</label>
				<label class="box1">{{educationArray[index1]}}</label>
			</picker>
			<br>
			<picker @change="bindPickerChange2" :range="workArray">
				<label class="searchway">工作方向：</label>
				<label class="box1">{{workArray[index2]}}</label>
			</picker>
			<button type="primary" size="mini" @click="sendSearchMessage()" style="margin-top: 20px;">搜索</button>
		</view>
		<view class="content2" style="font-size: 20px;font-weight: 800;"><pre>姓名&#9性别</pre></view>
		<view class="content2" v-for="(arritem,i) in arr" style="font-size: 15px;">
			<pre>{{arritem.name}}&#9{{arritem.gender}}
			<button type="default" id="showEmployee" size="mini" style="float: right;font-size: 10px;"
				@click="showEmployeeInfo(arritem)">详细信息</button></pre>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				arr:[],
				educationArray: ['不限', '高中', '本科', '硕士','博士'],
				workArray:['不限','前端工程师','后端工程师','市场营销','设计师'],
				index1:0,
				index2:0,
			}
		},
		methods: {
			sendSearchMessage()
			{
				this.arr=[];
				uni.request({
					url: 'http://123.57.94.22:9091/getJobHuntingByIdealPositionAndDegree.do',
					data: {
						idealPosition:this.workArray[this.index2],
						degree:this.educationArray[this.index1],
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						for (var i = 0; i <= res.data.length - 1; i++) {
							this.arr.push(res.data[i]);
						}
					}
				})
			},
			bindPickerChange1: function(e) {
				this.index1 = e.target.value;
			},
			bindPickerChange2: function(e) {
				this.index2 = e.target.value;
			},
			showEmployeeInfo(arrItem) {
				var email=arrItem.email;
				uni.request({
					url: 'http://123.57.94.22:9091/getEmployeeByEmail.do',
					complete: () => {},
					data: {
						email: email
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						console.log(res)
						if (res.data != null) {
							console.log(res)
							uni.navigateTo({
								url: '../showEmployee/showEmployee?email=' +email+
									'&ifShow=' + false
							})
						}
					}
				})
			},
		}
	}
</script>

<style>
	.content1 {
		display: flex;
		flex-direction: column;
		margin: 50px;
		font-size: 20px;
	}
	.content2 {
		padding-top: 20px;
	}
	.content3 {
		display: flex;
		flex-direction:column;
		margin-top: 20px;
		margin-bottom: 20px;
		align-items:auto;
	}
	.searchway {
		background-color: #B9DEF0;
		margin: 20px;
	}
	
	.box1 {
		background-color: #E2E2E2;
		width:
	}
</style>
