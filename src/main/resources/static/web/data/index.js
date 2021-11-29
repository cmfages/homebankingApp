const app = Vue.createApp({
    data() {
        return {
            client: {
                email: '',
                password: ''
            },
            clientRegister: {
                firstName: '',
                lastName: '',
                email: '',
                password: ''
            }
        }
    }, 
   methods: {
    clickSubmit() {
        console.log(this.client);
        axios.post('/api/login', `email=${this.client.email}&password=${this.client.password}`,
        {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response);
                window.location.href = '/web/home.html';
            })
            .catch(err => {
                console.log(err);
                swal('Error', 'Email or Password is incorrect', 'error');
            })
     },
    loginRegister(email, password) {
        axios.post('/api/login', `email=${email}&password=${password}`,
        {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response);
                setTimeout(() => window.location.href = '/web/home.html', 2000 );
                //window.location.href = '/web/home.html';
            })
            .catch(err => {
                console.log(err);
            })
    },
    registerClient() {
        console.log(this.clientRegister);
        axios.post('/api/clients', `firstName=${this.clientRegister.firstName}&lastName=${this.clientRegister.lastName}&email=${this.clientRegister.email}&password=${this.clientRegister.password}`,
        {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log(response);
                this.loginRegister(this.clientRegister.email, this.clientRegister.password);
                swal('Approved', 'Registration complete. Redirecting...')
            })
            .catch(err => {
                console.log(err);
                swal('Error', 'Email is already used', 'error');
            })
     }
   },
   computed: {
   },
   created() {

    }
})
app.mount("#app");