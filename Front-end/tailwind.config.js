/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js", // add this line
  ],
  theme: {
    theme: {
      extend: {
        width: {
          "7/10": "70%",
          "3/10": "30%",
        },
      },
    },
  },
  plugins: [require("flowbite/plugin")],
};
