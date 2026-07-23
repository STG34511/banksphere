import { Box, Stack, Typography } from "@mui/material";

import { TransferModeSelector } from "../components/TransferModeSelector";
import { RecipientSection } from "../components/RecipientSection";
import { TransferForm } from "../components/TransferForm";
import { TransferSummary } from "../components/TransferSummary";

export default function TransferPage() {
  return (
    <Stack spacing={4}>
      {/* Page Header */}
      <Box>
        <Typography variant="h4" sx={{ fontWeight: 700 }}>
          Transfer Money
        </Typography>

        <Typography variant="body1" color="text.secondary">
          Send money securely using BankSphere payment services.
        </Typography>
      </Box>

      {/* Transfer Mode */}
      <TransferModeSelector />

      {/* Main Content */}
      <Box
        sx={{
          display: "grid",
          gridTemplateColumns: { xs: "1fr", lg: "2fr 1fr" },
          gap: 3,
        }}
      >
        <Stack spacing={3}>
          <RecipientSection />

          <TransferForm />
        </Stack>

        <TransferSummary />
      </Box>
    </Stack>
  );
}