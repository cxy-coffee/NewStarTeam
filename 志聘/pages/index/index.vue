<template>
	<view class="content1">
		<text style="font-size: 30px;">员工信息：</text><input type="text" id="searchemployeeinfo"
			style="background-color:#f0f0f0" v-model="value" />
		<view class="content3">
			<picker @change="bindPickerChange" :range="array">
				<label class="searchway">查询方式：</label>
				<label class="box1">{{array[index]}}</label>
			</picker>
			<button type="primary" size="mini" @click="sendSearchMessage()" style="margin-top: 20px;">查询</button>
		</view>
		<view class="content2" style="font-size: 15px;">
			<pre>姓名&#9性别&#9员工号</pre>
		</view>
		<view class="content2" v-for="(arritem,i) in arr" style="font-size: 15px;">
			<pre>{{arritem.name}}&#9{{arritem.gender}}&#9{{arritem.accountNumber}}
			<button type="default" id="changeemployee" size="mini" style="float: right;font-size: 10px;"
				@click="changeEmployeeInfo(arritem)">修改</button>
			</pre>
		</view>
		<view style="justify-content: center; display:flex;flex-direction:row; align-items: center;">
			<button class="view2" type="default" size="mini" style="float: left;margin-left: 15px;"
				@click="minus()">上一页</button>
			<text>第{{index2}}页</text>
			<button class="view2" type="default" size="mini" style="float: right;margin-right: 15px;"
				@click="add()">下一页</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				arr: [],
				array: ['请选择', '按姓名查询', '按身份证查询', '按性别查询', '按邮箱查询'],
				index: 0,
				index2: 1,
				index1: 0,
				value: undefined,
				companyId: undefined,
				companyName: '',
				
			}
		},



		onLoad: function(options) {
			var that = this;
			uni.getStorage({
					key: 'companyId',
					success: function(res) {
						that.companyId = res.data.companyId;
					}
				}),
				console.log(this.companyId)
			uni.request({
				url: 'http://123.57.94.22:9091/getPresentEmployeesPage.do',
				data: {
					page: this.index2
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					console.log(res);

					for (var i = 0; i <= res.data.length - 1; i++) {
						this.arr.push(res.data[i]);

					}

				}
			});
			var that = this;
			uni.request({
				url: 'http://123.57.94.22:9091/getCompanyByCompanyId.do',
				data: '',
				complete: () => {},
				data: {
					companyId: this.companyId
				},
				header: {
					'content-type': 'application/x-www-form-urlencoded',
					'Accept': "application/json;charset=UTF-8"
				},
				method: 'POST',
				success: res => {
					var data = res.data.name;
					that.companyName = data;
					uni.setNavigationBarTitle({
						title: this.companyName
					})
				},
			})
		},







		methods: {
			add() {
				
				switch (this.index) {
					case 0: {
						uni.request({
							//改端口
							url: 'http://123.57.94.22:9091/getPresentEmployeesPage.do',
							data: {
								page: this.index2 + 1,
								

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

									this.arr = [];
									console.log(this.arr)
									for (var i = 0; i <= res.data.length - 1; i++) {

										this.arr.push(res.data[i]);

									}
									this.index2 = this.index2 + 1;
								}



							}

						});
					}
					break;
				case 1: {
					uni.request({
						//改端口
						url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndNamePage.do',
						data: {
							page: this.index2 + 1,
                            name: this.value,
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

								this.arr = [];
								console.log(this.arr)
								for (var i = 0; i <= res.data.length - 1; i++) {

									this.arr.push(res.data[i]);

								}
								this.index2 = this.index2 + 1;
							}



						}

					});

				}
				break;

				case 3: {
					uni.request({
						//改端口
						url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndGenderPage.do',
						data: {
							page: this.index2 + 1,
                            gender: this.value,
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

								this.arr = [];
								console.log(this.arr)
								for (var i = 0; i <= res.data.length - 1; i++) {

									this.arr.push(res.data[i]);

								}
								this.index2 = this.index2 + 1;
							}



						}

					});

				}
				break;
				default: {}
				break;
				}
			},
			minus() {
				switch (this.index) {
					case 0: {
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
								url: 'http://123.57.94.22:9091/getPresentEmployeesPage.do',
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
										this.arr = [];
										for (var i = 0; i <= res.data.length - 1; i++) {
											this.arr.push(res.data[i]);

										}
									}


								}

							})
						}



					}
					break;
				case 1: {
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
							url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndNamePage.do',
							data: {
								page: this.index2,
								name: this.value,

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
									this.arr = [];
									for (var i = 0; i <= res.data.length - 1; i++) {
										this.arr.push(res.data[i]);

									}
								}


							}

						})
					}




				}
				break;

				case 3: {
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
							url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndGenderPage.do',
							data: {
								page: this.index2,
                                gender: this.value
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
									this.arr = [];
									for (var i = 0; i <= res.data.length - 1; i++) {
										this.arr.push(res.data[i]);

									}
								}


							}

						})
					}
				}
				break;
				default: {}
				break;

				}


			},

			sendSearchMessage() {
				if (this.value !== undefined) {
					switch (this.index) {
						case 1: {
							console.log(this.value)
							uni.request({
								url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndNamePage.do',
								data: {
									name: this.value,
									page: this.index2,
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {

									this.arr = [];

									for (var i = 0; i <= res.data.length - 1; i++) {
										this.arr.push(res.data[i]);

									}



								}
							})
						}
						break;

					case 2: {
						uni.request({
							url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndIdentifyNumber.do',
							data: {
								identifyNumber: this.value,
							},
							header: {
								'content-type': 'application/x-www-form-urlencoded',
								'Accept': "application/json;charset=UTF-8"
							},
							method: 'POST',
							success: res => {
								this.arr = [];
								console.log(res);
								var len = res.data.length;
								for (var i = 0; i <= len - 1; i++) {
									this.arr.push(res.data[i]);
								}

							}
						})
					}
					break;
					case 3: {
						uni.request({
							url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndGenderPage.do',
							data: {
								gender: this.value,
								page:this.index2,
							},
							header: {
								'content-type': 'application/x-www-form-urlencoded',
								'Accept': "application/json;charset=UTF-8"
							},
							method: 'POST',
							success: res => {
								this.arr = [];
								console.log(res);
								var len = res.data.length;
								for (var i = 0; i <= len - 1; i++) {
									this.arr.push(res.data[i]);
								}

							}
						})
					}
					break;
					case 4: {

						uni.request({
							url: 'http://123.57.94.22:9091/getEmployeeByCompanyIdAndEmail.do',
							data: {
								email: this.value,
							},
							header: {
								'content-type': 'application/x-www-form-urlencoded',
								'Accept': "application/json;charset=UTF-8"
							},
							method: 'POST',
							success: res => {
								this.arr = [];
								console.log(res);
								var len = res.data.length;
								for (var i = 0; i <= len - 1; i++) {
									this.arr.push(res.data[i]);
								}

							}
						})

					}
					break;
					default:
						uni.showToast({
							title: '请选择操作！',
							icon: 'none',
							duration: 2000,
						})
						break;

					}
					

				} else {
					uni.showToast({
						title: '请输入要查询的员工！',
						icon: 'none',
						duration: 2000,
					})
				}
			},

			bindPickerChange: function(e) {
				this.index = e.target.value;
				console.log(this.index)
				
				
				
			},
			removeEmployeeInfo() {
				uni.showModal({
					title: '提示',
					content: '您确定要开除该员工吗',
					success: function(res) {
						if (res.confirm) {

							console.log("该员工被开除");
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})


			},



			changeEmployeeInfo(arrItem) {
				var item = JSON.stringify(arrItem);
				uni.navigateTo({
					url: "../changeEmployeeInfo/changeEmployeeInfo?item=" + item +
						"&companyId=" + this.companyId,
				})

			},
			showmore() {

				uni.navigateTo({
					url: "../showMore/showmore"
				})

			}

			//   //向后端发送请求
			//   getEmployeeInfo(){
			// 	  uni.request({
			// 	  	url:'',
			// 		success: (res) => {
			// 			this.arr=res.data;
			// 		}
			// 	  })


			// },
			// //页面创建未挂载的时候调用
			// created(){
			// 	this.getEmployeeInfo()
			// }







		}
	}
</script>

<style>
	#size {
		width: 20%;
		height: 20%;
	}


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
		flex-direction: column;
		margin-top: 20px;
		margin-bottom: 20px;
		align-items: center;
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


	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
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
