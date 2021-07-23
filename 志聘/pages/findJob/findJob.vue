<template>
	<view>
		<view class="box2">
			<view class="view1">-----请选择您的学历-----</view>
			<view class="content3">
				<picker @change="bindPickerChange1" :range="educationArray">
					<label class="searchway">学历：</label>
					<label class="box1">{{educationArray[index1]}}</label>
				</picker>
				<br>
			</view>
			<view class="view1">---请选择您的就职方向---</view>
			<view class="content3">
				<picker @change="bindPickerChange2" :range="workArray">
					<label class="searchway">工作方向：</label>
					<label class="box1">{{workArray[index2]}}</label>
				</picker>
				<button type="primary" size="mini" @click="saveSearchMessage()" style="margin-top: 20px;">保存信息</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				accountNumber:'',
				educationArray: ['请选择', '高中', '本科', '硕士','博士'],
				workArray:['请选择','前端工程师','后端工程师','市场营销','设计师'],
				index1:0,
				index2:0,
			}
		},
		methods: {
			onLoad: function(options)
			{
				this.accountNumber=options.accountNumber
			},
			saveSearchMessage()
			{
				if(this.index1!=0&&this.index2!=0)
				{
					uni.request({
						url: 'http://123.57.94.22:9091/goJobHunting.do',
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
							uni.showToast({
									title: '修改成功！请等待面试邀请！',
									icon: 'none',
									duration: 2000,
								})
							}
					})
				}
				else
				{
					uni.showToast({
							title: '请正确选择信息！',
							icon: 'none',
							duration: 2000,
						})
				}
			},
			bindPickerChange1: function(e) {
				this.index1 = e.target.value;
			},
			bindPickerChange2: function(e) {
				this.index2 = e.target.value;
			},
		}
	}
</script>

<style>
	.box2 {
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 70px;
		line-height: 50px;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
	}
	.view1 {
		text-align: center;
		font-size: 28px;
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
