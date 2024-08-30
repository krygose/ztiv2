<template>
  <div class="flex flex-col justify-center text-white">
    <div class="mx-auto mt-10 flex pt-6 text-2xl">
      <span>Don't have an account yet?</span>
    </div>

    <form
      method="post"
      class="login-form z-2 mx-auto flex w-80 flex-col p-6"
      @submit.prevent="registerUser"
    >
      <div class="mx-auto my-2 flex w-full flex-col gap-4">
        <InputField
          id="firstname"
          v-model="firstname"
          class="h-10 rounded bg-light-gray pl-2"
          type="text"
          name="firstname"
          placeholder="Jan"
          required
        />
        <InputField
          id="lastname"
          v-model="lastname"
          class="h-10 rounded bg-light-gray pl-2"
          type="text"
          name="lastname"
          placeholder="Kowalski"
        />
        <InputField
          id="email"
          v-model="email"
          class="h-10 rounded bg-light-gray pl-2"
          type="text"
          name="email"
          placeholder="you@gmail.com"
          required
        />
        <InputField
          id="password"
          v-model="password"
          class="h-10 rounded bg-light-gray pl-2"
          type="password"
          name="email"
          placeholder="password"
          required
        />
        <span v-if="isValid" class="pl-2 text-sm text-red-600">{{ error }}</span>
      </div>
      <button
        type="submit"
        class="submit-btn mx-auto my-6 w-full rounded-lg bg-gray-500 p-2 hover:bg-mcl-orange"
      >
        Register
      </button>
      <div class="mx-auto flex">
        <span class="p-1 text-sm">Already have an account?</span>
        <router-link
          to="/login"
          class="p-1 text-sm underline underline-offset-2 hover:text-mcl-orange"
          >Log In</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue'
import router from '@/router/index.js'
import axios from 'axios'

export default {
  name: 'RegisterForm',
  components: { InputField },
  data() {
    return {
      firstname: '',
      lastname: '',
      email: '',
      password: '',
      error: '',
      isValid: true
    }
  },
  methods: {
    async registerUser() {
      const response = await axios.post('api/users/register', {
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,
        password: this.password
      })

      if (response.status === 201) {
        router.push(this.returnUrl || '/login')
      }
      // }
    }
  }
}
</script>
