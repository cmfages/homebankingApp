const app = Vue.createApp({
    data() {
        return {
            client: {},
            account: [],
            transactions: []
        }
    },
    created() {
        setTimeout(() => this.loadData(), 1800 )
     }, 
    methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    let animation1 = this.$refs.cubeAnimation;
                    let animation2 = this.$refs.mainDiv;
                    animation1.style.display = "none";
                    animation2.style.display = "block";
                })
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('id');
        axios.get(`/api/accounts/${myParam}`)
                .then(resp => {
                    this.account = resp.data;
                    this.transactions = this.account.transactions;
                    console.log(this.account);
                    console.log(this.transactions);
                    
                })
    },
    sortById(accountArray){
        accountArray.sort((accountA, accountB) => {
            if(accountA.id < accountB.id){
                return -1
            }
            if(accountA.id > accountB.id){
                return 1
            }
            return 0
        })
        return accountArray
    },
    hasTransactions: function() {
        return this.transactions != 0;
    },
    signedOut() {
        axios.post('/api/logout')
            .then(response => {
                console.log(response);
                window.location.href = '/web/index.html';
            })
            .catch(err => {
                console.log(err);
            })
    }
   },
   computed: {
   }
})
app.mount("#app");