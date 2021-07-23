<template>
	<view>

		<view class="content5">
			<view class="content4">
				<view class="view1">-----员工基本信息-----</view>
				<view class="employeeinfoview">
					<text>姓名：<text>{{name}}</text></text>
				</view>
				<view class="employeeinfoview">
					<text>性别：<text>{{gender}}</text></text>
				</view>
				<view class="employeeinfoview">
					<text>生日：<text>{{birthday}}</text></text>
				</view>
				<view class="employeeinfoview">
					<text>邮箱：<text>{{email}}</text></text>
				</view>
				<view class="employeeinfoview">
					<text>身份证：<text>{{identifyNumber}}</text></text>
				</view>
				<view class="employeeinfoview">
					<text>工号：<text>{{accountNumber}}</text></text>
				</view>
				<button type="primary" class="changeinfobutton" @click="changeInfo()">修改信息</button>
			</view>
			<view class="content6">
				<view class="view1">-----过往评价-----</view>
				<view class="comment" v-for="item in experiences"
					style="margin: 0px;font-weight: 800;font-size: 20px;margin-left: 20px;">
					{{item.assessment.performance}}
				</view>
				<view style="justify-content: center; display:flex;flex-direction:row; align-items: center;">
					<button class="view2" type="default" size="mini" style="float: left;margin-left: 20px;"
						@click="minus()">上一页</button>
					<text>第{{index2}}页</text>
					<button class="view2" type="default" size="mini" style="float: right;margin-right: 20px;"
						@click="add()">下一页</button>
				</view>

			</view>
			<button type="primary" @click="findJob()">我要求职</button>
			<button type="primary" @click="finishFindJob()">结束求职</button>
			<button type="warn" @click="logout()">退出登录</button>

		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: undefined,
				birthday: undefined,
				gender: undefined,
				email: undefined,
				identifyNumber: undefined,
				accountNumber: undefined,
				experiences: undefined,
				assessments: undefined,
				index2: 1,
			}
		},
		onLoad: function() {

			uni.request({
				url: 'http://123.57.94.22:9091/selfRetrieveEmployeePage.do',
				data: {
					page: this.index2
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					console.log(res.data);
					this.name = res.data.name;
					this.birthday = res.data.birthday.toString().substr(0, 10);
					this.gender = res.data.gender;
					this.email = res.data.email;
					this.identifyNumber = res.data.identifyNumber;
					this.accountNumber = res.data.accountNumber;
					this.experiences = res.data.experiences
				}
			})
		},
		// mounted() {
		// 				var a =  document.getElementsByClassName('uni-page-head-hd')[0]
		// 				a.style.display = 'none'
		// 		},
		onBackPress(event) {
			uni.redirectTo({
				url: '../employeerigister/rigister'
			});
			return true;
		},


		methods: {
			add() {

				uni.request({
					//改端口
					url: 'http://123.57.94.22:9091/selfRetrieveEmployeePage.do',
					data: {
						page: this.index2 + 1,

					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {

						if (res.data.experiences.length == 0) {
							uni.showToast({
								title: '已经到最后一页了！',
								icon: 'none',
								duration: 2000
							})

						} else {
							
							this.experiences = [];
							console.log(this.experiences)
							for (var i = 0; i <= res.data.experiences.length - 1; i++) {

								this.experiences.push(res.data.experiences[i]);
                            
							}
							this.index2 = this.index2 + 1;
						}



					}

				});


			},
			minus() {
				this.index2 = this.index2 - 1;
				if (this.index2 <= 0) {
					uni.showToast({
						title: '已经到第一页了！',
						icon: 'none',
						duration: 2000
					})
					this.index2 = this.index2 + 1;
				} else {

					uni.request({
						//改端口
						url: 'http://123.57.94.22:9091/selfRetrieveEmployeePage.do',
						data: {
							page: this.index2,

						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
							if (res == []) {
								uni.showToast({
									title: '错误！',
									icon: 'none',
									duration: 2000
								})
							} else {
								this.experiences = [];
								for (var i = 0; i <= res.data.experiences.length - 1; i++) {
									this.experiences.push(res.data.experiences[i]);

								}
							}


						}

					})
				}
			},


			changeInfo() {
				uni.navigateTo({
					url: '../changeInfo/changeInfo?name=' + this.name +
						'&birthday=' + this.birthday +
						'&gender=' + this.gender +
						'&email=' + this.email +
						'&identifyNumber=' + this.identifyNumber +
						'&accountNumber=' + this.accountNumber,
				})


			},
			jumpToRigister() {
				uni.navigateTo({
					url: "../rigister/rigister"
				})

			},
			logout() {
				uni.request({

					url: 'http://123.57.94.22:9091/logout.do',

					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {}
				});
				uni.navigateTo({
					url: "../rigister/rigister"
				})


			},
			findJob() {
				uni.navigateTo({
					url: "../findJob/findJob?accountNumber=" + this.accountNumber
				})
			},
			finishFindJob() {
				uni.request({
					url: 'http://123.57.94.22:9091/stopJobHunting.do',
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						uni.showToast({
							title: '已结束求职！',
							icon: 'none',
							duration: 2000,
						})
					}
				});
			}


		}
	}
</script>

<style>
	.employeeinfoview {
		display: flex;
		flex-direction: column;
		margin-left: 30px;
		font-size: 20px;
		font-weight: 800;
	}

	,





	.content6 {
		margin-left: 20px;
		margin-right: 20px;
		height: 400px;
		margin-top: 20px;
		line-height: 50px;
		justify-content: center;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
		background-color: #DDDDDD;

	}

	.view2 {
		margin: 20px;
		border-radius: 10px;
		border: #09BB07 double;
		height: auto;
		font-family: 'Courier New', Courier, monospace;
		line-height: 25px;
		font-size: 10px;

	}


	.content4 {

		margin-left: 20px;
		margin-right: 20px;
		height: 400px;
		margin-top: 0px;
		line-height: 40px;
		justify-content: center;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
		background-color: #DDDDDD;

	}

	,



	.content5 {
		display: flex;
		flex-direction: column;
		justify-content: center;
		margin-top: 50px;
	}

	,

	.comment {

		margin-right: 30px;

	}


	.view1 {
		text-align: center;
		font-size: 28px;
	}

	,

	.changeinfobutton {
		margin-left: 110px;
		margin-right: 110px;
		margin-top: 20px;

	}
</style>
