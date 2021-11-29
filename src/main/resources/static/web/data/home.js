const app = Vue.createApp({
    data() {
        return {
            client: {}
        }
    },
    
   methods: {
    loadData() {
        axios.get("/api/clients/current")
                .then(resp => {
                    this.client = resp.data;
                    console.log(this.client);
                    let animation1 = this.$refs.cubeAnimation;
                    let animation2 = this.$refs.mainDiv;
                    animation1.style.display = "none";
                    animation2.style.display = "block";
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