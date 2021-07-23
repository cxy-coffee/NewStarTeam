<template>
	<view>
		<view class="menu">
			<view class="leftScroll">
				<view :class="isActive==index?'itemLeft':'itemLeft'" v-for="(item,index) in leftItems" :key="index"
					@click="chooseClick(index)">
					{{item.val}}
				</view>

			</view>

			<view class="rightScroll">
				<view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				isActive: 0,
				leftItems: [{
						val: '添加员工'
					},
					{
						val: '查看企业信息'
					},
					{
						val: '查看员工信息'
					},
					{
						val: '人才查找'
					},
					{
						val: '退出登录'
					},

				],
				companyId: undefined,
				array: [],
				company: undefined,
				companyName: undefined,
			}
		},
		mounted() {
			var a = document.getElementsByClassName('uni-page-head-hd')[0]
			a.style.display = 'none'
		},
		onShow() {
			var that = this;
			that.companyId=uni.getStorageSync("companyId");
		},
		onLoad: function(options) {
			var that = this;
			that.companyId=uni.getStorageSync("companyId");
				uni.getStorage({
					success: res => {
						console.log(res.keys);

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

			chooseClick(index) {
				switch (index) {
					case 0: {
						uni.navigateTo({
							url: "../add/add",
						})
					}
					break;
				case 1: {
					uni.navigateTo({
						url: "../companyinfo/companyinfo",
					})

				}
				break;
				case 2: {
					uni.navigateTo({
						url: "../index/index?companyId=" + this.companyId,
					})
					console.log(this.companyId);
				}
				break;
				case 3:{
									uni.navigateTo({
										url:"../searchEmployee/searchEmployee",
									})
								}break;
				
				case 4: {
					uni.showModal({
						title: '提示',
						content: '您确定要退出登录吗',
						success: function(res) {
							if (res.confirm) {
								uni.request({

									url: 'http://123.57.94.22:9091/logout.do',

									header: {
										'content-type': 'application/x-www-form-urlencoded',
										'Accept': "application/json;charset=UTF-8"
									},
									method: 'POST',
									success: res => {
										console.log("已退出")
									}
								});
								uni.navigateTo({
									url: "../rigister/rigister"
								})

							} else if (res.cancel) {
								console.log('用户点击取消');
							}
						}
					})
					uni.removeStorage({
						key: 'companyId',
						success: function() {

						}
					})

				}
				break;
				default: {
					uni.showToast({
						title: '出现异常！',
						icon: 'none',
						duration: 2000,
					})
				}

				break;




				}


			},

		}
	}
</script>

<style scoped>
	.companyview {
		display: flex;
		flex-direction: column;
		margin: 10px;

	}

	,

	.companybutton {
		margin: 20px;


	}

	.1 {
		background-color: #AAFFFF;
		border-top-left-radius: 20px;
		border-bottom-left-radius: 20px;
	}


	.menu {
		margin-top: 50px;
		padding: 0 15px;
		display: flex;
		justify-content: center;

	}

	.leftScroll {
		font-size: 28px;
		font-weight: 400;
		font-family: 'Times New Roman', Times, serif;
		background-color: #AAFFFF;
	}

	.itemLeft {
		width: 300px;
		height: 80px;
		line-height: 80px;
		text-align: center;
		font-weight: 800;
		color: #333333;
		background-color: #F3F3F3;
	}

	.itemLeftTwo {
		width: 178px;
		height: 80px;
		line-height: 80px;
		padding-left: 28px;
		background-color: #E2EDFF;
		color: #2F77F1;
		border-left: 4px solid #2F77F1;
	}


	.rightScroll {
		margin-left: 0px;

	}

	.bigConScro {
		padding: 0 30px;
	}

	.topTitle {
		margin-top: 20px;
		font-size: 28px;
		font-weight: 400;
		color: #333333;
	}

	.botCitysList {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		font-size: 24px;
		font-weight: 400px;
		color: #333333;
		margin-top: 44px;
	}

	.listItems {
		margin-bottom: 20px;
	}
</style>
