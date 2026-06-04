
export default {
  darkMode: ["class", '[data-theme="dark"]'],

  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],

  theme: {
    extend: {
      colors: {
        /* =========================
           CORE SURFACES
        ========================= */

        background: "var(--color-background)",
        surface: "var(--color-surface)",
        "surface-secondary": "var(--color-surface-secondary)",
        "surface-hover": "var(--color-surface-hover)",

        /* =========================
           TEXT COLORS
        ========================= */

        text: {
          primary: "var(--color-text-primary)",
          secondary: "var(--color-text-secondary)",
          muted: "var(--color-text-muted)",
          inverse: "var(--color-text-inverse)",
        },

        /* =========================
           PRIMARY BRAND
        ========================= */

        primary: {
          DEFAULT: "var(--color-primary)",
          light: "var(--color-primary-light)",
          dark: "var(--color-primary-dark)",
        },

        /* =========================
           SECONDARY
        ========================= */

        secondary: {
          DEFAULT: "var(--color-secondary)",
        },

        /* =========================
           ACCENT
        ========================= */

        accent: {
          DEFAULT: "var(--color-accent)",
          light: "var(--color-accent-light)",
          dark: "var(--color-accent-dark)",
        },

        /* =========================
           BORDERS
        ========================= */

        border: {
          DEFAULT: "var(--color-border)",
          light: "var(--color-border-light)",
          strong: "var(--color-border-strong)",
        },

        /* =========================
           STATUS COLORS
        ========================= */

        success: {
          DEFAULT: "var(--status-success)",
          bg: "var(--status-success-bg)",
        },

        warning: {
          DEFAULT: "var(--status-warning)",
          bg: "var(--status-warning-bg)",
        },

        danger: {
          DEFAULT: "var(--status-danger)",
          bg: "var(--status-danger-bg)",
        },

        info: {
          DEFAULT: "var(--status-info)",
          bg: "var(--status-info-bg)",
        },

        pending: {
          DEFAULT: "var(--status-pending)",
          bg: "var(--status-pending-bg)",
        },

        fraud: {
          DEFAULT: "var(--status-fraud)",
          bg: "var(--status-fraud-bg)",
        },
      },

      /* =========================
         TYPOGRAPHY
      ========================= */

      fontFamily: {
        sans: ["Inter", "sans-serif"],
      },

      fontSize: {
        xs: "var(--font-size-xs)",
        sm: "var(--font-size-sm)",
        base: "var(--font-size-md)",
        lg: "var(--font-size-lg)",
        xl: "var(--font-size-xl)",
        "2xl": "var(--font-size-2xl)",
        "3xl": "var(--font-size-3xl)",
        "4xl": "var(--font-size-4xl)",
      },

      fontWeight: {
        normal: "var(--font-weight-normal)",
        medium: "var(--font-weight-medium)",
        semibold: "var(--font-weight-semibold)",
        bold: "var(--font-weight-bold)",
      },

      /* =========================
         BORDER RADIUS
      ========================= */

      borderRadius: {
        xs: "var(--radius-xs)",
        sm: "var(--radius-sm)",
        md: "var(--radius-md)",
        lg: "var(--radius-lg)",
        xl: "var(--radius-xl)",
        "2xl": "var(--radius-2xl)",
      },

      /* =========================
         SHADOWS
      ========================= */

      boxShadow: {
        xs: "var(--shadow-xs)",
        sm: "var(--shadow-sm)",
        md: "var(--shadow-md)",
        lg: "var(--shadow-lg)",
        xl: "var(--shadow-xl)",
      },

      /* =========================
         LAYOUT
      ========================= */

      spacing: {
        header: "var(--header-height)",
        sidebar: "var(--sidebar-width)",
      },

      maxWidth: {
        content: "var(--content-max-width)",
      },

      /* =========================
         TRANSITIONS
      ========================= */

      transitionDuration: {
        fast: "150ms",
        normal: "250ms",
        slow: "400ms",
      },

      /* =========================
         KEYFRAMES
      ========================= */

      keyframes: {
        fadeIn: {
          from: {
            opacity: "0",
          },
          to: {
            opacity: "1",
          },
        },

        slideUp: {
          from: {
            opacity: "0",
            transform: "translateY(12px)",
          },
          to: {
            opacity: "1",
            transform: "translateY(0)",
          },
        },
      },

      /* =========================
         ANIMATIONS
      ========================= */

      animation: {
        fadeIn: "fadeIn 0.3s ease",
        slideUp: "slideUp 0.35s ease",
      },
    },
  },

  plugins: [],
};