import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  optimizeDeps: {
    include: ['proto/hello_pb', 'proto/HelloServiceClientPb']
  },
  build: {
    commonjsOptions: {
      include: [/proto/, /node_modules/],
    }
  }
})
