<template>
	<view class="administractorview">
		<view class="">
			<text style="margin-top:20px;margin-left: 20px; font-size: 28px;">审核栏</text>
		</view>
		<view class="administractor" v-for="(item,index) in Company" :key="index">
			{{item.name}}的注册正在等待审核
			<view class="" v-show="flag[index].state">
				公司名：{{item.name}}<br />
				公司法人代表名：{{item.legalRepresentativeName}}<br />
				公司邮箱：{{item.email}}<br />
				公司地址：{{item.address}}<br />
				公司电话：{{item.phone}}<br />
				<button type="primary" size="mini" @click="pass(index)">通过</button>
			</view>
			<button class="buttonadministractor" @click="check(index)">审核</button>

		</view>
		<view style="justify-content: center; display:flex;flex-direction:row; align-items: center;">
			<button class="view2" type="default" size="mini" style="float: left;margin-left: 20px;"
				@click="minus()">上一页</button>
			<text>第{{index2}}页</text>
			<button class="view2" type="default" size="mini" style="float: right;margin-right: 20px;"
				@click="add()">下一页</button>
		</view>

		<button type="warn" size="mini" @click="logout()"
			style="display: flex;justify-content: center;flex-direction: column;margin: 30px;">退出登录</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				Company: [
					// {
					// 		"name": "湖南省长沙市沙坪建设有限公司xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
					// 		"legalRepresentativeName": "刘权进",
					// 		"email": "1443352843@qq.com",
					// 		"address": "湖南省长沙市",
					// 		"phone": "18888878888"

					// 	},
					// 	{
					// 		"name": "湖南省长沙市沙坪建设有限公司xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
					// 		"legalRepresentativeName": "刘权进",
					// 		"email": "1443352843@qq.com",
					// 		"address": "湖南省长沙市",
					// 		"phone": "18888888888"

					// 	}
				],
				flag: [],
				index: 0,
				index2: 1,


			}
		},
		onLoad() {
			uni.request({
				//改端口
				url: 'http://123.57.94.22:9091/getCompanyToReviewPage.do',
				data: {
					page: this.index2,

				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					for (var i = 0; i <= res.data.length - 1; i++) {
						this.Company.push(res.data[i]);

						this.flag.push({
							state: false
						})

					}

				}

			})
		},


		methods: {
			add() {
				
				uni.request({
					//改端口
					url: 'http://123.57.94.22:9091/getCompanyToReviewPage.do',
					data: {
						page: this.index2+1,

					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {

						if (res.data.length == 0) {
							uni.showToast({
								title: '已经到最后一页了！',
								icon: 'none',
								duration: 2000
							})
							
						} else {
							this.Company = [];
							for (var i = 0; i <= res.data.length - 1; i++) {

								this.Company.push(res.data[i]);

								this.flag.push({
									state: false
								})
								
							}this.index2 = this.index2 + 1;
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
						url: 'http://123.57.94.22:9091/getCompanyToReviewPage.do',
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
								this.Company = [];
								for (var i = 0; i <= res.data.length - 1; i++) {
									this.Company.push(res.data[i]);

									this.flag.push({
										state: false
									})

								}
							}


						}

					})

				}
			},

			check(index) {
				this.flag[index].state = !this.flag[index].state;


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
				uni.showModal({
					title: '提示',
					content: '您确定要退出吗',
					success: function(res) {
						if (res.confirm) {
							uni.navigateTo({
								url: "../rigister/rigister"
							})

						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})




			},

			pass(index) {

				uni.request({
					//改端口
					url: 'http://123.57.94.22:9091/confirmCompanyRegisterApply.do',
					data: {
						companyId: this.Company[index].companyId,
						name: this.Company[index].name,
						legalRepresentativeName: this.Company[index].legalRepresentativeName,
						email: this.Company[index].email,
						address: this.Company[index].address,
						phone: this.Company[index].phone,

					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {

						this.Company.splice(index, 1)

					}

				})

			}


		}




	}
</script>

<style>
	page {
		background-color: #AAFFFF;
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


	.administractorview {
		margin: 10px;
	}

	.administractor {
		margin: 20px;
		border-radius: 10px;
		border: #09BB07 double;
		height: auto;
		font-family: 'Courier New', Courier, monospace;
		line-height: 25px;
		font-size: 10px;
	}

	.buttonadministractor {
		float: right;
		border: #007AFF double;
		font-size: 10px;
		line-height: 18px;







	}
</style>
