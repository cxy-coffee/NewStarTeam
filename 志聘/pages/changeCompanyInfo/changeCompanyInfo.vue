<template>
	<view style="font-size: 25px;margin-left: 25px;margin-right: 25px;">

		<view class="uni-form-item">部门名
			<input class="uni-input" v-model="name" style="font-size: 25px;margin-top: 10px;margin-bottom: 10px;">
		</view>
		<view class="uni-form-item">公司ID<br />
			<text class="uni-input" style="font-size: 25px;margin-top: 10px;background-color: #FFFFFF;">{{companyId}}</text>
		</view>
		<view class="uni-form-item">部门ID
			<input class="uni-input" v-model="departmentId" style="height: 40px;margin-top: 10px;margin-bottom: 10px;">
		</view>
		<view class="uni-form-item1">
			<text style="font-weight: 800;font-size: 25px;">职位名&#12288&#12288职位ID</text>
			<view class="showpositionview" v-for="(item,index) in positions">
				{{item.name}}&#12288&#12288&#12288{{item.positionId}}
				<view class="showPosition" v-show="flag[index].state">
					<view>
						<text style="font-size: 10px;">职位：</text>
						<input type="text" value="" v-model="item.name" />
					</view>
					<view>
						<text style="font-size: 10px;">编号：</text>
						<input type="text" value="" v-model="item.positionId" />
					</view>


				</view>
				<button type="default" size="mini"
					style="float: right;border: #007AFF double;font-size: 10px;line-height: 18px;"@click="removePosition(item)">删除</button>

				<button type="default" size="mini" v-show="index1[index].state"
					style="float: right;border: #007AFF double;font-size: 10px;line-height: 18px;"
					@click="ChangePositions(index)">修改</button>
				<button type="default" size="mini" v-show="!index1[index].state"
					style="float: right;border: #007AFF double;font-size: 10px;line-height: 18px;"
					@click="ChangePosition(item,index)">确认修改</button>

			</view>
		</view>
		<button type="primary" @click="ensureChange()" style="margin-top: 10px;">部门修改确认</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: '',
				companyId: '',
				departmentId: '',
				positions: [],
				flag: [{
						state: false
					},
					{
						state: false
					}
				],
				index1: [{
						state: true
					},
					{
						state: true
					}
				],

			}
		},
		onLoad: function(arritem) {
			var item = JSON.parse(arritem.item); // 字符串转对象
			this.name = item.name;
			this.companyId = item.companyId;
			this.departmentId = item.departmentId;
			for (var i = 0; i <= item.positions.length - 1; i++) {
				this.positions.push(item.positions[i])
				this.flag.push({state:false})
				this.index1.push({state:true})
			}

		},
		methods: {
			ChangePositions(index) {
				this.flag[index].state = !this.flag[index].state;
				this.index1[index].state = !this.index1[index].state


			},
			removePosition(item,index){
				uni.showModal({
					title: '提示',
					content: '您确定要删除您公司部门的该职位吗',
					success: (res) => {
						if (res.confirm) {
							uni.request({
								url: 'http://123.57.94.22:9091/deletePosition.do',
								data: {
									companyId: this.companyId,
									departmentId: this.departmentId,
									positionId :item.positionId,
							
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									if (res.data) {
										uni.showToast({
												title: '删除成功！',
												icon: 'none',
												duration: 2000,
											}),
											this.positions.splice(index,1);
											uni.navigateTo({
												url: '../companyinfo/companyinfo'
											})
									} else {
										uni.showToast({
											title: '异常！请联系管理员！',
											icon: 'none',
											duration: 2000,
										})
									}
				
								}
							})
				
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
			},
			
			
			ChangePosition(item,index) {
				uni.showModal({
					title: '提示',
					content: '您确定要修改您公司该职位信息吗',
					success: (res) => {
						if (res.confirm) {
							uni.request({
								url: 'http://123.57.94.22:9091/updatePosition.do',
								data: {
									companyId: this.companyId,
									departmentId: this.departmentId,
									name: item.name,
									positionId: item.positionId,
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									if (res.data) {
										uni.showToast({
												title: '修改成功！',
												icon: 'none',
												duration: 2000,
											}),
											uni.navigateTo({
												url: '../companyinfo/companyinfo'
											})
									} else {
										uni.showToast({
											title: '修改失败，请检查输入！',
											icon: 'none',
											duration: 2000,
										})
									}

								}
							})

						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
               this.flag[index].state = !this.flag[index].state;
               this.index1[index].state = !this.index1[index].state
			},

			ensureChange() {
				uni.showModal({
					title: '提示',
					content: '您确定要修改您公司该部门信息吗',
					success: (res) => {
						if (res.confirm) {
							uni.request({
								url: 'http://123.57.94.22:9091/updateDepartment.do',
								data: {
									companyId: this.companyId,
									departmentId: this.departmentId,
									name: this.name,
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									if (res.data) {
										uni.showToast({
												title: '修改成功！',
												icon: 'none',
												duration: 2000,
											}),
											uni.navigateTo({
												url: '../companyinfo/companyinfo'
											})
									} else {
										uni.showToast({
											title: '修改失败，请检查输入！',
											icon: 'none',
											duration: 2000,
										})
									}

								}
							})

						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})


			}
		}
	}
</script>

<style>
	.uni-input {
		background-color: #F0F0F0;
	}

	,
	.uni-form-item {
		font-weight: 800;
		font-size: 20px;
	}

	.uni-form-item1 {
		border: #09BB07 double;
		margin-top: 10px;

	}

	.showpositionview {

		margin: 20px;

		border-radius: 10px;
		border: #09BB07 double;
		height: auto;
		font-family: 'Courier New', Courier, monospace;
		line-height: 25px;
		font-size: 20px;

	}
</style>
