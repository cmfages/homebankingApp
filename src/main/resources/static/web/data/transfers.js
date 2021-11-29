const app = Vue.createApp({
    data() {
        return {
            client: {},
            accounts: [],
            fromAccNumber: "",
            toAccNumber: "",
            inputAmount: 0,
            inputDescription: ""
        }
    },
    
   methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    this.accounts = this.sortById(resp.data.accounts);
                    console.log(this.client);
                    console.log(this.accounts);
                    let animation1 = this.$refs.cubeAnimation;
                    let animation2 = this.$refs.mainDiv;
                    animation1.style.display = "none";
                    animation2.style.display = "block";
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
    newTransfer() {
        console.log(this.fromAccNumber)
        console.log(this.toAccNumber)
        console.log(this.inputAmount);
        console.log(this.inputDescription);
        axios.post('/api/transactions', `originAccountNumber=${this.fromAccNumber}&destinationAccountNumber=${this.toAccNumber}&amount=${this.inputAmount}&description=${this.inputDescription}`)
            .then(response => {
                console.log(response);
                swal('Approved', 'Transfer successful')
                setTimeout(() => location.reload(), 2500 );
            })
            .catch(err => {
                swal('Error', err.response.data, 'error');
                console.log(err);
            })
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
    },
    created() {
        setTimeout(() => this.loadData(), 1800 )
    }
})
app.mount("#app");