import {
  Box,
  Button,
  Paper,
  Stack,
  Tab,
  Tabs,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";

export function RecipientSection() {
  const [tab, setTab] = useState(0);

  return (
    <Paper sx={{ p: 3 }}>
      <Stack spacing={3}>
        <Box
          display="flex"
          justifyContent="space-between"
          alignItems="center"
        >
          <Typography variant="h6">
            Recipient
          </Typography>

          <Button size="small">
            Manage Beneficiaries
          </Button>
        </Box>

        <Tabs
          value={tab}
          onChange={(_, value) => setTab(value)}
          variant="fullWidth"
        >
          <Tab label="Saved Beneficiary" />
          <Tab label="New Account" />
        </Tabs>

        {tab === 0 ? (
          <Stack spacing={2}>
            <TextField
              select
              fullWidth
              label="Select Beneficiary"
              helperText="No beneficiaries available."
            />

            <Typography
              variant="body2"
              color="text.secondary"
            >
              Save beneficiaries to transfer faster in the future.
            </Typography>
          </Stack>
        ) : (
          <Stack spacing={2}>
            <TextField
              label="Account Number"
              fullWidth
            />

            <TextField
              label="Account Holder Name"
              fullWidth
            />

            <TextField
              label="IFSC Code"
              fullWidth
            />

            {/* Later */}

            {/* <FormControlLabel
                control={<Checkbox />}
                label="Save as beneficiary"
            /> */}
          </Stack>
        )}
      </Stack>
    </Paper>
  );
}