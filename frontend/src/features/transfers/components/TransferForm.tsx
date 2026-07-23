import {
  Button,
  Paper,
  Stack,
  TextField,
  Typography,
} from "@mui/material";

export function TransferForm() {
  return (
    <Paper sx={{ p: 3 }}>
      <Stack spacing={3}>
        <Typography variant="h6">
          Transfer Details
        </Typography>

        <TextField
          label="Amount"
          type="number"
          fullWidth
        />

        <TextField
          label="Remarks"
          multiline
          rows={3}
          fullWidth
        />

        <Button
          variant="contained"
          size="large"
        >
          Continue
        </Button>
      </Stack>
    </Paper>
  );
}