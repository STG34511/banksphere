import { Card, CardActionArea, Grid, Typography, Chip } from "@mui/material";

const transferModes = [
  {
    title: "Internal",
    description: "Instant transfer within BankSphere",
    enabled: true,
  },
  {
    title: "IMPS",
    description: "24x7 interbank transfer",
    enabled: false,
  },
  {
    title: "NEFT",
    description: "Batch settlement",
    enabled: false,
  },
  {
    title: "RTGS",
    description: "High-value real-time transfer",
    enabled: false,
  },
];

export function TransferModeSelector() {
  return (
    <Grid container spacing={2}>
      {transferModes.map((mode) => (
        <Grid size={{ xs: 12, md: 6, lg: 3 }} key={mode.title}>
          <Card
            variant={mode.enabled ? "elevation" : "outlined"}
            sx={{
              opacity: mode.enabled ? 1 : 0.55,
              border: mode.enabled ? 2 : 1,
              borderColor: mode.enabled ? "primary.main" : "divider",
            }}
          >
            <CardActionArea disabled={!mode.enabled}>
              <Typography variant="h6" sx={{ p: 2 }}>
                {mode.title}
              </Typography>

              <Typography
                variant="body2"
                color="text.secondary"
                sx={{ p: 2, pb: 2 }}
              >
                {mode.description}
              </Typography>

              {!mode.enabled && (
                <Chip label="Coming Soon" size="small" sx={{ ml: 2, mb: 2 }} />
              )}
            </CardActionArea>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
}
