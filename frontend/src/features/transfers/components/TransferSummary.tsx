import {
  Divider,
  Paper,
  Stack,
  Typography,
} from "@mui/material";

export function TransferSummary() {
  return (
    <Paper sx={{ p: 3 }}>
      <Stack spacing={2}>
        <Typography variant="h6">
          Transfer Summary
        </Typography>

        <SummaryRow
          label="Transfer Type"
          value="Internal"
        />

        <SummaryRow
          label="Recipient"
          value="-"
        />

        <SummaryRow
          label="Amount"
          value="₹0.00"
        />

        <SummaryRow
          label="Transfer Fee"
          value="₹0.00"
        />

        <Divider />

        <SummaryRow
          label="Total Debit"
          value="₹0.00"
          bold
        />
      </Stack>
    </Paper>
  );
}

type SummaryRowProps = {
  label: string;
  value: string;
  bold?: boolean;
};

function SummaryRow({
  label,
  value,
  bold = false,
}: SummaryRowProps) {
  return (
    <Stack
      direction="row"
      sx={{
        justifyContent: "space-between",
        alignItems: "center",
      }}
    >
      <Typography
        sx={{
          fontWeight: bold ? 700 : 400,
        }}
      >
        {label}
      </Typography>

      <Typography
        sx={{
          fontWeight: bold ? 700 : 500,
        }}
      >
        {value}
      </Typography>
    </Stack>
  );
}